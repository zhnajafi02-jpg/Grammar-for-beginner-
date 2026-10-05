package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.audio.TtsAudioEngine
import com.example.data.local.BookmarkEntity
import com.example.data.local.GrammarDatabase
import com.example.data.local.UserPreferencesEntity
import com.example.data.local.UserProgressEntity
import com.example.data.model.GrammarData
import com.example.data.model.GrammarUnit
import com.example.data.model.ListeningExercise
import com.example.data.model.MinimalPair
import com.example.data.repository.GrammarRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

sealed class Screen {
  object Home : Screen()
  data class UnitDetail(val unitId: String) : Screen()
  data class ExerciseSession(val unitId: String) : Screen()
  object ListeningHub : Screen()
  object Bookmarks : Screen()
  object Settings : Screen()
}

class GrammarViewModel(application: Application) : AndroidViewModel(application) {
  private val database = GrammarDatabase.getDatabase(application)
  private val repository = GrammarRepository(database.progressDao())
  val audioEngine = TtsAudioEngine(application)

  val units: List<GrammarUnit> = repository.getAllUnits()
  val minimalPairs: List<MinimalPair> = repository.getMinimalPairs()

  val progressMap: StateFlow<Map<String, UserProgressEntity>> = repository.allProgress
    .map { list -> list.associateBy { it.unitId } }
    .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyMap())

  val bookmarks: StateFlow<List<BookmarkEntity>> = repository.allBookmarks
    .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

  val preferences: StateFlow<UserPreferencesEntity> = repository.userPreferences
    .map { it ?: UserPreferencesEntity() }
    .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), UserPreferencesEntity())

  // Screen Navigation State
  private val _screenStack = MutableStateFlow<List<Screen>>(listOf(Screen.Home))
  val currentScreen: StateFlow<Screen> = _screenStack
    .map { it.lastOrNull() ?: Screen.Home }
    .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Screen.Home)

  // Audio state
  val isAudioPlaying: StateFlow<Boolean> = audioEngine.isSpeaking
  val currentUtteranceId: StateFlow<String?> = audioEngine.currentUtteranceId
  val isTtsReady: StateFlow<Boolean> = audioEngine.isReady

  // Continuous player state (for Listening Hub)
  private val _continuousPlayingIndex = MutableStateFlow<Int?>(null)
  val continuousPlayingIndex: StateFlow<Int?> = _continuousPlayingIndex.asStateFlow()

  // Active Exercise Session State
  private val _activeExerciseUnit = MutableStateFlow<GrammarUnit?>(null)
  val activeExerciseUnit: StateFlow<GrammarUnit?> = _activeExerciseUnit.asStateFlow()

  private val _currentExerciseIndex = MutableStateFlow(0)
  val currentExerciseIndex: StateFlow<Int> = _currentExerciseIndex.asStateFlow()

  private val _exerciseScore = MutableStateFlow(0)
  val exerciseScore: StateFlow<Int> = _exerciseScore.asStateFlow()

  private val _isAnswerChecked = MutableStateFlow(false)
  val isAnswerChecked: StateFlow<Boolean> = _isAnswerChecked.asStateFlow()

  private val _isCurrentAnswerCorrect = MutableStateFlow(false)
  val isCurrentAnswerCorrect: StateFlow<Boolean> = _isCurrentAnswerCorrect.asStateFlow()

  private val _selectedOption = MutableStateFlow<String?>(null)
  val selectedOption: StateFlow<String?> = _selectedOption.asStateFlow()

  private val _userSubmittedAnswer = MutableStateFlow("")
  val userSubmittedAnswer: StateFlow<String> = _userSubmittedAnswer.asStateFlow()

  // For Word Order exercise
  private val _orderedWords = MutableStateFlow<List<String>>(emptyList())
  val orderedWords: StateFlow<List<String>> = _orderedWords.asStateFlow()

  private val _availableWords = MutableStateFlow<List<String>>(emptyList())
  val availableWords: StateFlow<List<String>> = _availableWords.asStateFlow()

  private val _isSessionFinished = MutableStateFlow(false)
  val isSessionFinished: StateFlow<Boolean> = _isSessionFinished.asStateFlow()

  init {
    viewModelScope.launch {
      preferences.collect { prefs ->
        audioEngine.setSpeed(prefs.speechRate)
        audioEngine.setAccent(prefs.accent)
      }
    }
  }

  // Navigation functions
  fun navigateTo(screen: Screen) {
    audioEngine.stop()
    _screenStack.value = _screenStack.value + screen
  }

  fun navigateBack(): Boolean {
    audioEngine.stop()
    if (_screenStack.value.size > 1) {
      _screenStack.value = _screenStack.value.dropLast(1)
      return true
    }
    return false
  }

  // Audio Playback
  fun playAudio(text: String, id: String = text, speedMultiplier: Float = 1.0f) {
    audioEngine.speak(text, id, speedMultiplier)
  }

  fun playAudioSlow(text: String, id: String = text) {
    audioEngine.speak(text, id, 0.65f)
  }

  fun stopAudio() {
    audioEngine.stop()
    _continuousPlayingIndex.value = null
  }

  fun updateSpeedPreference(speed: Float) {
    viewModelScope.launch {
      val current = preferences.value
      repository.updatePreferences(speed, current.accent)
      audioEngine.setSpeed(speed)
    }
  }

  fun updateAccentPreference(accent: String) {
    viewModelScope.launch {
      val current = preferences.value
      repository.updatePreferences(current.speechRate, accent)
      audioEngine.setAccent(accent)
    }
  }

  fun toggleBookmark(english: String, persian: String, grammarNote: String = "", unitId: String = "") {
    viewModelScope.launch {
      val isBookmarked = bookmarks.value.any { it.englishText == english }
      repository.toggleBookmark(english, persian, grammarNote, unitId, isBookmarked)
    }
  }

  // Exercise Workflow
  fun startExerciseSession(unitId: String) {
    val unit = GrammarData.getUnitById(unitId) ?: return
    _activeExerciseUnit.value = unit
    _currentExerciseIndex.value = 0
    _exerciseScore.value = 0
    _isSessionFinished.value = false
    setupCurrentExercise(unit.exercises.firstOrNull())
    navigateTo(Screen.ExerciseSession(unitId))
  }

  private fun setupCurrentExercise(exercise: ListeningExercise?) {
    _isAnswerChecked.value = false
    _isCurrentAnswerCorrect.value = false
    _selectedOption.value = null
    _userSubmittedAnswer.value = ""
    if (exercise != null) {
      if (exercise.type == com.example.data.model.ExerciseType.WORD_ORDER) {
        _availableWords.value = exercise.scrambledWords.shuffled()
        _orderedWords.value = emptyList()
      } else {
        _availableWords.value = emptyList()
        _orderedWords.value = emptyList()
      }
      // Auto play audio prompt when starting exercise
      playAudio(exercise.audioPrompt, "exercise_${exercise.id}")
    }
  }

  fun selectWordForOrder(word: String) {
    if (_isAnswerChecked.value) return
    val currentAvailable = _availableWords.value.toMutableList()
    val wordIndex = currentAvailable.indexOf(word)
    if (wordIndex != -1) {
      currentAvailable.removeAt(wordIndex)
      _availableWords.value = currentAvailable
      _orderedWords.value = _orderedWords.value + word
    }
  }

  fun deselectWordForOrder(index: Int) {
    if (_isAnswerChecked.value) return
    val currentOrdered = _orderedWords.value.toMutableList()
    if (index in currentOrdered.indices) {
      val word = currentOrdered.removeAt(index)
      _orderedWords.value = currentOrdered
      _availableWords.value = _availableWords.value + word
    }
  }

  fun selectOption(option: String) {
    if (_isAnswerChecked.value) return
    _selectedOption.value = option
  }

  fun checkAnswer() {
    val unit = _activeExerciseUnit.value ?: return
    val exercises = unit.exercises
    val currentIndex = _currentExerciseIndex.value
    if (currentIndex !in exercises.indices) return
    val exercise = exercises[currentIndex]

    val submitted = when (exercise.type) {
      com.example.data.model.ExerciseType.WORD_ORDER -> _orderedWords.value.joinToString(" ").trim()
      else -> _selectedOption.value?.trim() ?: ""
    }
    _userSubmittedAnswer.value = submitted

    val isCorrect = when (exercise.type) {
      com.example.data.model.ExerciseType.WORD_ORDER -> {
        val correct = exercise.correctAnswer.trim()
        // Compare case insensitive and ignore trailing punctuation
        submitted.replace(Regex("[.,?!]"), "").equals(correct.replace(Regex("[.,?!]"), ""), ignoreCase = true)
      }
      else -> {
        submitted.equals(exercise.correctAnswer.trim(), ignoreCase = true)
      }
    }

    _isCurrentAnswerCorrect.value = isCorrect
    _isAnswerChecked.value = true
    if (isCorrect) {
      _exerciseScore.value += 1
    }
  }

  fun retryCurrentExercise() {
    val unit = _activeExerciseUnit.value ?: return
    val exercises = unit.exercises
    val currentIndex = _currentExerciseIndex.value
    if (currentIndex in exercises.indices) {
      setupCurrentExercise(exercises[currentIndex])
    }
  }

  fun nextExercise() {
    val unit = _activeExerciseUnit.value ?: return
    val exercises = unit.exercises
    val nextIndex = _currentExerciseIndex.value + 1

    if (nextIndex < exercises.size) {
      _currentExerciseIndex.value = nextIndex
      setupCurrentExercise(exercises[nextIndex])
    } else {
      // Completed all exercises
      _isSessionFinished.value = true
      viewModelScope.launch {
        repository.saveUnitCompletion(unit.id, _exerciseScore.value, exercises.size)
      }
    }
  }

  fun retryExerciseSession() {
    val unit = _activeExerciseUnit.value ?: return
    startExerciseSession(unit.id)
  }

  override fun onCleared() {
    super.onCleared()
    audioEngine.shutdown()
  }
}
