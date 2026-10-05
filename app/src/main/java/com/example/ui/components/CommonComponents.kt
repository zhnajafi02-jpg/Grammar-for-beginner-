package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material.icons.filled.SlowMotionVideo
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.AccentAmber
import com.example.ui.theme.AccentGreen
import com.example.ui.theme.AccentRed

@Composable
fun StarRatingRow(
  stars: Int,
  modifier: Modifier = Modifier,
  starSize: Int = 18
) {
  Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.spacedBy(2.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    for (i in 1..3) {
      Icon(
        imageVector = if (i <= stars) Icons.Default.Star else Icons.Default.StarBorder,
        contentDescription = "ستاره $i",
        tint = if (i <= stars) AccentAmber else MaterialTheme.colorScheme.outline.copy(alpha = 0.5f),
        modifier = Modifier.size(starSize.dp)
      )
    }
  }
}

@Composable
fun ExerciseProgressBar(
  currentIndex: Int,
  totalCount: Int,
  score: Int,
  modifier: Modifier = Modifier
) {
  Column(modifier = modifier.fillMaxWidth()) {
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text(
        text = "تمرین ${currentIndex + 1} از $totalCount",
        fontSize = 13.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onSurfaceVariant
      )
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
      ) {
        Icon(
          Icons.Default.Check,
          contentDescription = null,
          tint = AccentGreen,
          modifier = Modifier.size(16.dp)
        )
        Text(
          text = "امتیاز: $score",
          fontSize = 13.sp,
          fontWeight = FontWeight.Bold,
          color = AccentGreen
        )
      }
    }
    Spacer(modifier = Modifier.height(6.dp))
    LinearProgressIndicator(
      progress = { (currentIndex + 1).toFloat() / totalCount },
      modifier = Modifier
        .fillMaxWidth()
        .height(8.dp)
        .clip(RoundedCornerShape(4.dp)),
      color = MaterialTheme.colorScheme.primary,
      trackColor = MaterialTheme.colorScheme.surfaceVariant
    )
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun WordScrambleBuilder(
  orderedWords: List<String>,
  availableWords: List<String>,
  onSelectWord: (String) -> Unit,
  onDeselectWord: (Int) -> Unit,
  isAnswerChecked: Boolean,
  isCorrect: Boolean = false,
  correctSentence: String = "",
  modifier: Modifier = Modifier
) {
  val containerColor = when {
    isAnswerChecked && isCorrect -> AccentGreen.copy(alpha = 0.12f)
    isAnswerChecked && !isCorrect -> AccentRed.copy(alpha = 0.10f)
    else -> MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)
  }

  val borderColor = when {
    isAnswerChecked && isCorrect -> AccentGreen
    isAnswerChecked && !isCorrect -> AccentRed
    else -> MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)
  }

  Column(modifier = modifier.fillMaxWidth()) {
    // Constructed sentence drop area
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .height(115.dp)
        .testTag("word_order_drop_zone"),
      shape = RoundedCornerShape(16.dp),
      colors = CardDefaults.cardColors(containerColor = containerColor),
      border = BorderStroke(2.dp, borderColor)
    ) {
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .padding(12.dp),
        contentAlignment = Alignment.CenterStart
      ) {
        if (orderedWords.isEmpty()) {
          Text(
            text = "کلمات را برای ساخت جمله انتخاب کنید...",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
            modifier = Modifier.align(Alignment.Center)
          )
        } else {
          FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            orderedWords.forEachIndexed { index, word ->
              val wordColor = when {
                isAnswerChecked && isCorrect -> AccentGreen
                isAnswerChecked && !isCorrect -> AccentRed
                else -> MaterialTheme.colorScheme.primary
              }

              Surface(
                onClick = { if (!isAnswerChecked) onDeselectWord(index) },
                shape = RoundedCornerShape(10.dp),
                color = wordColor,
                contentColor = Color.White,
                shadowElevation = 2.dp,
                modifier = Modifier.testTag("ordered_word_$index")
              ) {
                Text(
                  text = word,
                  modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                  fontSize = 15.sp,
                  fontWeight = FontWeight.Bold
                )
              }
            }
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Available scrambled words (disabled after checking)
    FlowRow(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 4.dp),
      horizontalArrangement = Arrangement.spacedBy(8.dp),
      verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      availableWords.forEachIndexed { idx, word ->
        Surface(
          onClick = { if (!isAnswerChecked) onSelectWord(word) },
          shape = RoundedCornerShape(10.dp),
          color = MaterialTheme.colorScheme.surface,
          border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)),
          shadowElevation = 1.dp,
          modifier = Modifier.testTag("available_word_$idx")
        ) {
          Text(
            text = word,
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface
          )
        }
      }
    }
  }
}

@Composable
fun MultipleChoiceOptions(
  options: List<String>,
  selectedOption: String?,
  correctAnswer: String,
  isAnswerChecked: Boolean,
  onSelectOption: (String) -> Unit,
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier.fillMaxWidth(),
    verticalArrangement = Arrangement.spacedBy(10.dp)
  ) {
    options.forEachIndexed { index, option ->
      val isSelected = selectedOption == option
      val isCorrectOption = option.trim().equals(correctAnswer.trim(), ignoreCase = true)

      val backgroundColor = when {
        isAnswerChecked && isCorrectOption -> AccentGreen.copy(alpha = 0.15f)
        isAnswerChecked && isSelected && !isCorrectOption -> AccentRed.copy(alpha = 0.15f)
        isSelected -> MaterialTheme.colorScheme.primaryContainer
        else -> MaterialTheme.colorScheme.surface
      }

      val borderColor = when {
        isAnswerChecked && isCorrectOption -> AccentGreen
        isAnswerChecked && isSelected && !isCorrectOption -> AccentRed
        isSelected -> MaterialTheme.colorScheme.primary
        else -> MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)
      }

      Card(
        modifier = Modifier
          .fillMaxWidth()
          .testTag("exercise_option_$index")
          .clickable(enabled = !isAnswerChecked) { onSelectOption(option) },
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        border = BorderStroke(if (isAnswerChecked && (isCorrectOption || isSelected)) 2.5.dp else 1.5.dp, borderColor)
      ) {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          Column(modifier = Modifier.weight(1f)) {
            Text(
              text = option,
              fontSize = 16.sp,
              fontWeight = if (isSelected || (isAnswerChecked && isCorrectOption)) FontWeight.Bold else FontWeight.Medium,
              color = MaterialTheme.colorScheme.onSurface
            )

            // Direct inline badge
            if (isAnswerChecked) {
              if (isCorrectOption) {
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                  text = "✓ پاسخ درست",
                  fontSize = 12.sp,
                  fontWeight = FontWeight.Bold,
                  color = AccentGreen
                )
              } else if (isSelected) {
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                  text = "✗ انتخاب نادرست شما",
                  fontSize = 12.sp,
                  fontWeight = FontWeight.Bold,
                  color = AccentRed
                )
              }
            }
          }

          if (isAnswerChecked) {
            if (isCorrectOption) {
              Box(
                modifier = Modifier
                  .size(32.dp)
                  .clip(CircleShape)
                  .background(AccentGreen.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
              ) {
                Icon(
                  Icons.Default.Check,
                  contentDescription = "درست",
                  tint = AccentGreen,
                  modifier = Modifier.size(20.dp)
                )
              }
            } else if (isSelected) {
              Box(
                modifier = Modifier
                  .size(32.dp)
                  .clip(CircleShape)
                  .background(AccentRed.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
              ) {
                Icon(
                  Icons.Default.Close,
                  contentDescription = "نادرست",
                  tint = AccentRed,
                  modifier = Modifier.size(20.dp)
                )
              }
            }
          }
        }
      }
    }
  }
}

/**
 * Comprehensive Instant Feedback System for Beginner English Grammar
 * Tells the user whether the answer was right or wrong, compares choices,
 * provides concise Persian explanations, allows listening to correct audio,
 * and offers an immediate re-try.
 */
@Composable
fun InstantFeedbackCard(
  isCorrect: Boolean,
  userAnswer: String,
  correctAnswer: String,
  persianTranslation: String,
  explanationFa: String,
  wrongExplanationFa: String = "",
  grammarRuleTipFa: String = "",
  audioPrompt: String,
  onPlayAudio: (String, Float) -> Unit,
  onRetry: () -> Unit,
  onNext: () -> Unit,
  modifier: Modifier = Modifier
) {
  val statusColor = if (isCorrect) AccentGreen else AccentRed
  val statusBgColor = if (isCorrect) AccentGreen.copy(alpha = 0.12f) else AccentRed.copy(alpha = 0.10f)

  Surface(
    modifier = modifier
      .fillMaxWidth()
      .testTag("instant_feedback_panel"),
    color = MaterialTheme.colorScheme.surface,
    shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
    shadowElevation = 16.dp,
    border = BorderStroke(1.5.dp, statusColor.copy(alpha = 0.4f))
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 20.dp, vertical = 18.dp)
    ) {
      // 1. Header Banner: Right or Wrong
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .clip(RoundedCornerShape(12.dp))
          .background(statusBgColor)
          .padding(horizontal = 14.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        Box(
          modifier = Modifier
            .size(36.dp)
            .clip(CircleShape)
            .background(statusColor),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = if (isCorrect) Icons.Default.Check else Icons.Default.Close,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(22.dp)
          )
        }

        Column(modifier = Modifier.weight(1f)) {
          Text(
            text = if (isCorrect) "آفرین! پاسخ شما کاملاً درست است 🎉" else "پاسخ شما نادرست بود!",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = statusColor
          )
          Text(
            text = if (isCorrect) "دقت شنیداری و گرامری عالی بود." else "به توضیح گرامری زیر توجه کنید تا ملکه ذهنتان شود:",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }
      }

      Spacer(modifier = Modifier.height(14.dp))

      // 2. Comparison box if incorrect: Your answer vs Correct answer
      if (!isCorrect) {
        Card(
          shape = RoundedCornerShape(14.dp),
          colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)),
          modifier = Modifier.fillMaxWidth()
        ) {
          Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            if (userAnswer.isNotBlank()) {
              Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
              ) {
                Text(
                  text = "پاسخ شما:",
                  fontSize = 13.sp,
                  fontWeight = FontWeight.Bold,
                  color = AccentRed
                )
                Text(
                  text = userAnswer,
                  fontSize = 14.sp,
                  color = AccentRed,
                  fontWeight = FontWeight.SemiBold
                )
              }
            }

            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
              Text(
                text = "پاسخ صحیح:",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = AccentGreen
              )
              Text(
                text = correctAnswer,
                fontSize = 14.sp,
                color = AccentGreen,
                fontWeight = FontWeight.Bold
              )
            }
          }
        }
        Spacer(modifier = Modifier.height(10.dp))
      }

      // 3. Persian Translation
      if (persianTranslation.isNotEmpty()) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          Text(
            text = "معنی فارسی:",
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
          )
          Text(
            text = persianTranslation,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurface
          )
        }
        Spacer(modifier = Modifier.height(8.dp))
      }

      // 4. Grammar Explanation (Brief and clear explanation for errors)
      val effectiveExplanation = if (!isCorrect && wrongExplanationFa.isNotBlank()) {
        wrongExplanationFa
      } else {
        explanationFa
      }

      if (effectiveExplanation.isNotEmpty()) {
        Card(
          shape = RoundedCornerShape(12.dp),
          colors = CardDefaults.cardColors(
            containerColor = if (isCorrect) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f)
            else AccentRed.copy(alpha = 0.08f)
          ),
          modifier = Modifier.fillMaxWidth()
        ) {
          Row(
            modifier = Modifier.padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.Top
          ) {
            Icon(
              imageVector = if (isCorrect) Icons.Default.Lightbulb else Icons.Default.HelpOutline,
              contentDescription = null,
              tint = if (isCorrect) MaterialTheme.colorScheme.primary else AccentRed,
              modifier = Modifier.size(20.dp)
            )
            Column {
              Text(
                text = if (isCorrect) "نکته تکمیلی گرامر:" else "دلیل گرامری و توضیح اشتباه:",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = if (isCorrect) MaterialTheme.colorScheme.primary else AccentRed
              )
              Spacer(modifier = Modifier.height(2.dp))
              Text(
                text = effectiveExplanation,
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurface,
                lineHeight = 19.sp
              )
            }
          }
        }
        Spacer(modifier = Modifier.height(8.dp))
      }

      // 5. Grammar Rule Tip formula if available
      if (grammarRuleTipFa.isNotEmpty()) {
        Surface(
          shape = RoundedCornerShape(8.dp),
          color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f),
          modifier = Modifier.fillMaxWidth()
        ) {
          Text(
            text = "📌 قاعده: $grammarRuleTipFa",
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
          )
        }
        Spacer(modifier = Modifier.height(10.dp))
      }

      // 6. Audio listening to correct pronunciation
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        FilledTonalButton(
          onClick = { onPlayAudio(audioPrompt, 1.0f) },
          modifier = Modifier
            .weight(1f)
            .height(38.dp)
            .testTag("feedback_listen_normal"),
          shape = RoundedCornerShape(10.dp)
        ) {
          Icon(Icons.Default.VolumeUp, contentDescription = null, modifier = Modifier.size(16.dp))
          Spacer(modifier = Modifier.width(4.dp))
          Text("شنیدن تلفظ صحیح", fontSize = 12.sp, fontWeight = FontWeight.Bold)
        }

        FilledTonalButton(
          onClick = { onPlayAudio(audioPrompt, 0.65f) },
          modifier = Modifier
            .weight(1f)
            .height(38.dp)
            .testTag("feedback_listen_slow"),
          shape = RoundedCornerShape(10.dp)
        ) {
          Icon(Icons.Default.SlowMotionVideo, contentDescription = null, modifier = Modifier.size(16.dp))
          Spacer(modifier = Modifier.width(4.dp))
          Text("تلفظ آهسته ۰.۷x", fontSize = 12.sp)
        }
      }

      Spacer(modifier = Modifier.height(14.dp))

      // 7. Action Buttons (Try Again or Next)
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        if (!isCorrect) {
          OutlinedButton(
            onClick = onRetry,
            modifier = Modifier
              .weight(1f)
              .height(48.dp)
              .testTag("feedback_retry_button"),
            shape = RoundedCornerShape(12.dp)
          ) {
            Icon(Icons.Default.Replay, contentDescription = null, modifier = Modifier.size(18.dp))
            Spacer(modifier = Modifier.width(4.dp))
            Text("تلاش مجدد", fontSize = 14.sp, fontWeight = FontWeight.Bold)
          }
        }

        Button(
          onClick = onNext,
          modifier = Modifier
            .weight(if (!isCorrect) 1.2f else 1f)
            .height(48.dp)
            .testTag("feedback_next_button"),
          colors = ButtonDefaults.buttonColors(
            containerColor = if (isCorrect) AccentGreen else MaterialTheme.colorScheme.primary
          ),
          shape = RoundedCornerShape(12.dp)
        ) {
          Text(
            text = "ادامه و تمرین بعدی",
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
          )
        }
      }
    }
  }
}
