package com.example.audio

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Locale

class TtsAudioEngine(context: Context) : TextToSpeech.OnInitListener {
  private val tag = "TtsAudioEngine"

  private var tts: TextToSpeech? = null
  private val _isReady = MutableStateFlow(false)
  val isReady: StateFlow<Boolean> = _isReady.asStateFlow()

  private val _isSpeaking = MutableStateFlow(false)
  val isSpeaking: StateFlow<Boolean> = _isSpeaking.asStateFlow()

  private val _currentUtteranceId = MutableStateFlow<String?>(null)
  val currentUtteranceId: StateFlow<String?> = _currentUtteranceId.asStateFlow()

  private var currentSpeed: Float = 1.0f
  private var currentAccent: String = "US" // "US" or "UK"
  private var onUtteranceFinished: (() -> Unit)? = null

  init {
    tts = TextToSpeech(context.applicationContext, this)
  }

  override fun onInit(status: Int) {
    if (status == TextToSpeech.SUCCESS) {
      applyLocale(currentAccent)
      tts?.setSpeechRate(currentSpeed)
      tts?.setPitch(1.0f)

      tts?.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
        override fun onStart(utteranceId: String?) {
          _isSpeaking.value = true
          _currentUtteranceId.value = utteranceId
        }

        override fun onDone(utteranceId: String?) {
          _isSpeaking.value = false
          _currentUtteranceId.value = null
          onUtteranceFinished?.invoke()
          onUtteranceFinished = null
        }

        @Deprecated("Deprecated in Java")
        override fun onError(utteranceId: String?) {
          _isSpeaking.value = false
          _currentUtteranceId.value = null
          onUtteranceFinished = null
        }

        override fun onError(utteranceId: String?, errorCode: Int) {
          Log.e(tag, "TTS Error: $errorCode on $utteranceId")
          _isSpeaking.value = false
          _currentUtteranceId.value = null
          onUtteranceFinished = null
        }
      })
      _isReady.value = true
      Log.d(tag, "TTS Initialized successfully")
    } else {
      Log.e(tag, "Failed to initialize TTS, status: $status")
      _isReady.value = false
    }
  }

  fun setSpeed(speed: Float) {
    currentSpeed = speed.coerceIn(0.5f, 2.0f)
    tts?.setSpeechRate(currentSpeed)
  }

  fun setAccent(accent: String) {
    currentAccent = accent
    applyLocale(accent)
  }

  private fun applyLocale(accent: String) {
    val locale = if (accent == "UK") Locale.UK else Locale.US
    val result = tts?.setLanguage(locale)
    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
      Log.w(tag, "Language not supported: $locale, falling back to US")
      tts?.setLanguage(Locale.US)
    }
  }

  fun speak(
    text: String,
    utteranceId: String = System.currentTimeMillis().toString(),
    speedMultiplier: Float = 1.0f,
    onDone: (() -> Unit)? = null
  ) {
    if (!_isReady.value) {
      Log.w(tag, "TTS is not ready yet")
      return
    }

    onUtteranceFinished = onDone
    // Temporarily adjust speed for this utterance if requested
    val effectiveSpeed = (currentSpeed * speedMultiplier).coerceIn(0.5f, 2.0f)
    tts?.setSpeechRate(effectiveSpeed)

    tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, utteranceId)
  }

  fun stop() {
    tts?.stop()
    _isSpeaking.value = false
    _currentUtteranceId.value = null
  }

  fun shutdown() {
    stop()
    tts?.shutdown()
    tts = null
    _isReady.value = false
  }
}
