package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Celebration
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material.icons.filled.SlowMotionVideo
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.ExerciseType
import com.example.ui.components.AudioWaveVisualizer
import com.example.ui.components.ExerciseProgressBar
import com.example.ui.components.InstantFeedbackCard
import com.example.ui.components.MultipleChoiceOptions
import com.example.ui.components.StarRatingRow
import com.example.ui.components.WordScrambleBuilder
import com.example.ui.theme.AccentAmber
import com.example.ui.theme.AccentGreen
import com.example.ui.theme.PrimaryBlue
import com.example.ui.viewmodel.GrammarViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListeningExerciseScreen(
  unitId: String,
  viewModel: GrammarViewModel,
  modifier: Modifier = Modifier
) {
  BackHandler {
    viewModel.navigateBack()
  }

  val unit = viewModel.activeExerciseUnit.collectAsState().value ?: return
  val currentIndex by viewModel.currentExerciseIndex.collectAsState()
  val score by viewModel.exerciseScore.collectAsState()
  val isAnswerChecked by viewModel.isAnswerChecked.collectAsState()
  val isCorrect by viewModel.isCurrentAnswerCorrect.collectAsState()
  val selectedOption by viewModel.selectedOption.collectAsState()
  val userSubmittedAnswer by viewModel.userSubmittedAnswer.collectAsState()
  val orderedWords by viewModel.orderedWords.collectAsState()
  val availableWords by viewModel.availableWords.collectAsState()
  val isFinished by viewModel.isSessionFinished.collectAsState()
  val isAudioPlaying by viewModel.isAudioPlaying.collectAsState()

  val exercises = unit.exercises
  val currentExercise = exercises.getOrNull(currentIndex)

  Scaffold(
    topBar = {
      TopAppBar(
        title = {
          Text(
            text = "تمرین شنیداری: ${unit.titleFa}",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1
          )
        },
        navigationIcon = {
          IconButton(
            onClick = { viewModel.navigateBack() },
            modifier = Modifier.testTag("exercise_back_button")
          ) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "بازگشت")
          }
        },
        colors = TopAppBarDefaults.topAppBarColors(
          containerColor = MaterialTheme.colorScheme.surface
        )
      )
    },
    bottomBar = {
      if (!isFinished && isAnswerChecked && currentExercise != null) {
        InstantFeedbackCard(
          isCorrect = isCorrect,
          userAnswer = userSubmittedAnswer,
          correctAnswer = currentExercise.correctAnswer,
          persianTranslation = currentExercise.persianTranslation,
          explanationFa = currentExercise.explanationFa,
          wrongExplanationFa = currentExercise.wrongExplanationFa,
          grammarRuleTipFa = currentExercise.grammarRuleTipFa,
          audioPrompt = currentExercise.audioPrompt,
          onPlayAudio = { text, speed -> viewModel.playAudio(text, "feedback_audio", speed) },
          onRetry = { viewModel.retryCurrentExercise() },
          onNext = { viewModel.nextExercise() }
        )
      }
    },
    modifier = modifier.fillMaxSize()
  ) { paddingValues ->
    if (isFinished) {
      ExerciseCompletionView(
        unitTitle = unit.titleFa,
        score = score,
        total = exercises.size,
        onRetry = { viewModel.retryExerciseSession() },
        onReturnToLesson = { viewModel.navigateBack() },
        modifier = Modifier
          .fillMaxSize()
          .padding(paddingValues)
      )
    } else if (currentExercise != null) {
      Column(
        modifier = Modifier
          .fillMaxSize()
          .padding(paddingValues)
          .padding(horizontal = 16.dp, vertical = 12.dp)
          .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
      ) {
        // Progress bar
        ExerciseProgressBar(
          currentIndex = currentIndex,
          totalCount = exercises.size,
          score = score
        )

        // Audio Prompt Big Card
        Card(
          shape = RoundedCornerShape(20.dp),
          colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)
          ),
          modifier = Modifier.fillMaxWidth()
        ) {
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
              Icon(
                Icons.Default.Headphones,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(20.dp)
              )
              Text(
                text = "دقت به تلفظ و کلمات صوتی",
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary
              )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Big Play Button
            Surface(
              onClick = {
                viewModel.playAudio(currentExercise.audioPrompt, "exercise_${currentExercise.id}")
              },
              shape = CircleShape,
              color = MaterialTheme.colorScheme.primary,
              shadowElevation = 4.dp,
              modifier = Modifier
                .size(72.dp)
                .testTag("exercise_play_prompt_button")
            ) {
              Box(contentAlignment = Alignment.Center) {
                Icon(
                  imageVector = Icons.Default.VolumeUp,
                  contentDescription = "پخش مجدد صوت",
                  tint = MaterialTheme.colorScheme.onPrimary,
                  modifier = Modifier.size(36.dp)
                )
              }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
              // Slow audio option
              FilledTonalButton(
                onClick = {
                  viewModel.playAudioSlow(currentExercise.audioPrompt, "exercise_slow_${currentExercise.id}")
                },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.testTag("exercise_slow_play_button")
              ) {
                Icon(
                  Icons.Default.SlowMotionVideo,
                  contentDescription = null,
                  modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text("پخش آرام (0.7x)", fontSize = 12.sp)
              }

              if (isAudioPlaying) {
                AudioWaveVisualizer(isAnimating = true)
              }
            }
          }
        }

        // Instruction text
        Text(
          text = currentExercise.instructionFa,
          fontSize = 16.sp,
          fontWeight = FontWeight.Bold,
          color = MaterialTheme.colorScheme.onSurface,
          lineHeight = 22.sp
        )

        // Exercise Interaction depending on type
        when (currentExercise.type) {
          ExerciseType.WORD_ORDER -> {
            WordScrambleBuilder(
              orderedWords = orderedWords,
              availableWords = availableWords,
              onSelectWord = { word -> viewModel.selectWordForOrder(word) },
              onDeselectWord = { index -> viewModel.deselectWordForOrder(index) },
              isAnswerChecked = isAnswerChecked,
              isCorrect = isCorrect,
              correctSentence = currentExercise.correctAnswer
            )
          }
          else -> {
            MultipleChoiceOptions(
              options = currentExercise.options,
              selectedOption = selectedOption,
              correctAnswer = currentExercise.correctAnswer,
              isAnswerChecked = isAnswerChecked,
              onSelectOption = { option -> viewModel.selectOption(option) }
            )
          }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Check Answer button (visible only before checking)
        if (!isAnswerChecked) {
          val canCheck = when (currentExercise.type) {
            ExerciseType.WORD_ORDER -> orderedWords.isNotEmpty()
            else -> selectedOption != null
          }

          Button(
            onClick = { viewModel.checkAnswer() },
            enabled = canCheck,
            modifier = Modifier
              .fillMaxWidth()
              .height(50.dp)
              .testTag("check_answer_button"),
            colors = ButtonDefaults.buttonColors(
              containerColor = MaterialTheme.colorScheme.primary
            ),
            shape = RoundedCornerShape(14.dp)
          ) {
            Text(
              text = "بررسی پاسخ",
              fontSize = 16.sp,
              fontWeight = FontWeight.Bold
            )
          }
        }

        Spacer(modifier = Modifier.height(60.dp))
      }
    }
  }
}

@Composable
fun ExerciseCompletionView(
  unitTitle: String,
  score: Int,
  total: Int,
  onRetry: () -> Unit,
  onReturnToLesson: () -> Unit,
  modifier: Modifier = Modifier
) {
  val percentage = if (total > 0) ((score.toFloat() / total) * 100).toInt() else 0
  val stars = when {
    percentage >= 90 -> 3
    percentage >= 60 -> 2
    percentage >= 30 -> 1
    else -> 0
  }

  Column(
    modifier = modifier
      .padding(24.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ) {
    Box(
      modifier = Modifier
        .size(90.dp)
        .clip(CircleShape)
        .background(MaterialTheme.colorScheme.primaryContainer),
      contentAlignment = Alignment.Center
    ) {
      Icon(
        Icons.Default.Celebration,
        contentDescription = null,
        tint = MaterialTheme.colorScheme.primary,
        modifier = Modifier.size(50.dp)
      )
    }

    Spacer(modifier = Modifier.height(16.dp))

    Text(
      text = "آفرین! تمرین به پایان رسید",
      fontSize = 22.sp,
      fontWeight = FontWeight.Bold,
      color = MaterialTheme.colorScheme.onSurface
    )

    Spacer(modifier = Modifier.height(6.dp))

    Text(
      text = unitTitle,
      fontSize = 15.sp,
      color = MaterialTheme.colorScheme.onSurfaceVariant
    )

    Spacer(modifier = Modifier.height(18.dp))

    StarRatingRow(stars = stars, starSize = 36)

    Spacer(modifier = Modifier.height(16.dp))

    Card(
      shape = RoundedCornerShape(16.dp),
      colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
      ),
      modifier = Modifier.fillMaxWidth(0.85f)
    ) {
      Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Text(
          text = "$percentage% دقت شنیداری",
          fontSize = 20.sp,
          fontWeight = FontWeight.Bold,
          color = if (percentage >= 60) AccentGreen else MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
          text = "$score پاسخ صحیح از مجموع $total تمرین",
          fontSize = 14.sp,
          color = MaterialTheme.colorScheme.onSurfaceVariant
        )
      }
    }

    Spacer(modifier = Modifier.height(28.dp))

    Button(
      onClick = onReturnToLesson,
      modifier = Modifier
        .fillMaxWidth(0.85f)
        .height(50.dp)
        .testTag("completion_return_button"),
      shape = RoundedCornerShape(14.dp)
    ) {
      Text("بازگشت به درس و سرفصل‌ها", fontSize = 15.sp, fontWeight = FontWeight.Bold)
    }

    Spacer(modifier = Modifier.height(10.dp))

    OutlinedButton(
      onClick = onRetry,
      modifier = Modifier
        .fillMaxWidth(0.85f)
        .height(50.dp)
        .testTag("completion_retry_button"),
      shape = RoundedCornerShape(14.dp)
    ) {
      Icon(Icons.Default.Replay, contentDescription = null, modifier = Modifier.size(18.dp))
      Spacer(modifier = Modifier.width(6.dp))
      Text("تکرار مجدد تمرین شنیداری", fontSize = 15.sp)
    }
  }
}
