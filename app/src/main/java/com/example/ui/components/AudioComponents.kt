package com.example.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SlowMotionVideo
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.AudioSentence
import com.example.ui.theme.AccentAmber
import com.example.ui.theme.PrimaryBlue

@Composable
fun AudioWaveVisualizer(
  isAnimating: Boolean,
  modifier: Modifier = Modifier,
  color: Color = MaterialTheme.colorScheme.primary
) {
  val transition = rememberInfiniteTransition(label = "wave")
  val bar1 by transition.animateFloat(
    initialValue = 0.2f,
    targetValue = 0.9f,
    animationSpec = infiniteRepeatable(
      animation = tween(400, easing = FastOutSlowInEasing),
      repeatMode = RepeatMode.Reverse
    ),
    label = "bar1"
  )
  val bar2 by transition.animateFloat(
    initialValue = 0.8f,
    targetValue = 0.3f,
    animationSpec = infiniteRepeatable(
      animation = tween(500, delayMillis = 100, easing = FastOutSlowInEasing),
      repeatMode = RepeatMode.Reverse
    ),
    label = "bar2"
  )
  val bar3 by transition.animateFloat(
    initialValue = 0.3f,
    targetValue = 1.0f,
    animationSpec = infiniteRepeatable(
      animation = tween(350, delayMillis = 50, easing = FastOutSlowInEasing),
      repeatMode = RepeatMode.Reverse
    ),
    label = "bar3"
  )
  val bar4 by transition.animateFloat(
    initialValue = 0.7f,
    targetValue = 0.25f,
    animationSpec = infiniteRepeatable(
      animation = tween(450, delayMillis = 150, easing = FastOutSlowInEasing),
      repeatMode = RepeatMode.Reverse
    ),
    label = "bar4"
  )

  Canvas(modifier = modifier.size(width = 36.dp, height = 24.dp)) {
    val barWidth = 4.dp.toPx()
    val gap = 4.dp.toPx()
    val heights = if (isAnimating) listOf(bar1, bar2, bar3, bar4) else listOf(0.2f, 0.2f, 0.2f, 0.2f)

    heights.forEachIndexed { index, fraction ->
      val x = index * (barWidth + gap) + barWidth / 2
      val barHeight = size.height * fraction
      val top = (size.height - barHeight) / 2
      drawLine(
        color = color,
        start = Offset(x, top),
        end = Offset(x, top + barHeight),
        strokeWidth = barWidth,
        cap = StrokeCap.Round
      )
    }
  }
}

@Composable
fun AudioPlayController(
  isSpeaking: Boolean,
  onPlayNormal: () -> Unit,
  onPlaySlow: () -> Unit,
  onStop: () -> Unit,
  modifier: Modifier = Modifier
) {
  Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    if (isSpeaking) {
      FilledTonalIconButton(
        onClick = onStop,
        modifier = Modifier
          .size(48.dp)
          .testTag("audio_stop_button"),
        colors = IconButtonDefaults.filledTonalIconButtonColors(
          containerColor = MaterialTheme.colorScheme.errorContainer,
          contentColor = MaterialTheme.colorScheme.error
        )
      ) {
        Icon(Icons.Default.Stop, contentDescription = "توقف صدا")
      }
    } else {
      FilledTonalIconButton(
        onClick = onPlayNormal,
        modifier = Modifier
          .size(48.dp)
          .testTag("audio_play_button"),
        colors = IconButtonDefaults.filledTonalIconButtonColors(
          containerColor = MaterialTheme.colorScheme.primaryContainer,
          contentColor = MaterialTheme.colorScheme.primary
        )
      ) {
        Icon(Icons.Default.VolumeUp, contentDescription = "پخش تلفظ")
      }
    }

    // Slow button (0.65x)
    Surface(
      onClick = onPlaySlow,
      shape = RoundedCornerShape(12.dp),
      color = MaterialTheme.colorScheme.surfaceVariant,
      modifier = Modifier
        .height(40.dp)
        .testTag("audio_slow_button")
    ) {
      Row(
        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
      ) {
        Icon(
          Icons.Default.SlowMotionVideo,
          contentDescription = "پخش آهسته",
          modifier = Modifier.size(18.dp),
          tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
          text = "0.7x آهسته",
          fontSize = 12.sp,
          fontWeight = FontWeight.Medium,
          color = MaterialTheme.colorScheme.onSurfaceVariant
        )
      }
    }

    if (isSpeaking) {
      AudioWaveVisualizer(isAnimating = true)
    }
  }
}

@Composable
fun SentenceAudioCard(
  sentence: AudioSentence,
  isSpeaking: Boolean,
  isBookmarked: Boolean,
  onPlayNormal: () -> Unit,
  onPlaySlow: () -> Unit,
  onStop: () -> Unit,
  onToggleBookmark: () -> Unit,
  modifier: Modifier = Modifier
) {
  Card(
    modifier = modifier
      .fillMaxWidth()
      .testTag("sentence_card_${sentence.id}"),
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(
      containerColor = MaterialTheme.colorScheme.surface
    ),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
      ) {
        Column(modifier = Modifier.weight(1f)) {
          Text(
            text = sentence.english,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            lineHeight = 24.sp
          )
          if (sentence.phonetic.isNotEmpty()) {
            Text(
              text = sentence.phonetic,
              fontSize = 13.sp,
              fontFamily = FontFamily.Monospace,
              color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
              modifier = Modifier.padding(top = 2.dp)
            )
          }
        }

        IconButton(
          onClick = onToggleBookmark,
          modifier = Modifier
            .size(40.dp)
            .testTag("bookmark_button_${sentence.id}")
        ) {
          Icon(
            imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
            contentDescription = "ذخیره در نشان‌شده‌ها",
            tint = if (isBookmarked) AccentAmber else MaterialTheme.colorScheme.onSurfaceVariant
          )
        }
      }

      Spacer(modifier = Modifier.height(8.dp))

      Text(
        text = sentence.persian,
        fontSize = 15.sp,
        fontWeight = FontWeight.Medium,
        color = MaterialTheme.colorScheme.onSurface
      )

      if (sentence.audioTip.isNotEmpty()) {
        Spacer(modifier = Modifier.height(8.dp))
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5f))
            .padding(horizontal = 10.dp, vertical = 6.dp)
        ) {
          Text(
            text = "🎧 نکته شنیداری: ${sentence.audioTip}",
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSecondaryContainer
          )
        }
      }

      Spacer(modifier = Modifier.height(12.dp))

      AudioPlayController(
        isSpeaking = isSpeaking,
        onPlayNormal = onPlayNormal,
        onPlaySlow = onPlaySlow,
        onStop = onStop
      )
    }
  }
}
