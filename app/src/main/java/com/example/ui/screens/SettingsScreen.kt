package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.AccentGreen
import com.example.ui.viewmodel.GrammarViewModel
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
  viewModel: GrammarViewModel,
  modifier: Modifier = Modifier
) {
  BackHandler {
    viewModel.navigateBack()
  }

  val preferences by viewModel.preferences.collectAsState()
  val isTtsReady by viewModel.isTtsReady.collectAsState()

  Scaffold(
    topBar = {
      TopAppBar(
        title = {
          Text(
            text = "تنظیمات صوت و تلفظ",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
          )
        },
        navigationIcon = {
          IconButton(
            onClick = { viewModel.navigateBack() },
            modifier = Modifier.testTag("settings_back_button")
          ) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "بازگشت")
          }
        },
        colors = TopAppBarDefaults.topAppBarColors(
          containerColor = MaterialTheme.colorScheme.surface
        )
      )
    },
    modifier = modifier.fillMaxSize()
  ) { paddingValues ->
    LazyColumn(
      modifier = Modifier
        .fillMaxSize()
        .padding(paddingValues),
      contentPadding = PaddingValues(16.dp),
      verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
      // TTS Status Card
      item {
        Card(
          shape = RoundedCornerShape(16.dp),
          colors = CardDefaults.cardColors(
            containerColor = if (isTtsReady) AccentGreen.copy(alpha = 0.12f) else MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.5f)
          ),
          modifier = Modifier.fillMaxWidth()
        ) {
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
          ) {
            Icon(
              imageVector = if (isTtsReady) Icons.Default.Check else Icons.Default.Info,
              contentDescription = null,
              tint = if (isTtsReady) AccentGreen else MaterialTheme.colorScheme.error
            )
            Column {
              Text(
                text = if (isTtsReady) "موتور تلفظ صوتی (TTS) فعال است" else "در حال راه‌اندازی موتور صوتی...",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = if (isTtsReady) AccentGreen else MaterialTheme.colorScheme.error
              )
              Text(
                text = "پشتیبانی کامل از تلفظ آفلاین و کنترل سرعت گفتار",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
              )
            }
          }
        }
      }

      // Accent Selector
      item {
        Card(
          shape = RoundedCornerShape(16.dp),
          colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
          elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
          modifier = Modifier.fillMaxWidth()
        ) {
          Column(modifier = Modifier.padding(16.dp)) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
              Icon(Icons.Default.Translate, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
              Text(
                text = "لهجه گوینده انگلیسی",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
              )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
              text = "لهجه مورد نظر خود را برای شنیدن تلفظ جملات انتخاب کنید:",
              fontSize = 13.sp,
              color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
              FilterChip(
                selected = preferences.accent == "US",
                onClick = { viewModel.updateAccentPreference("US") },
                label = { Text("آمریکایی (American US)") },
                modifier = Modifier.testTag("accent_us_chip")
              )
              FilterChip(
                selected = preferences.accent == "UK",
                onClick = { viewModel.updateAccentPreference("UK") },
                label = { Text("بریتانیایی (British UK)") },
                modifier = Modifier.testTag("accent_uk_chip")
              )
            }
          }
        }
      }

      // Speed Slider
      item {
        Card(
          shape = RoundedCornerShape(16.dp),
          colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
          elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
          modifier = Modifier.fillMaxWidth()
        ) {
          Column(modifier = Modifier.padding(16.dp)) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.SpaceBetween,
              modifier = Modifier.fillMaxWidth()
            ) {
              Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
              ) {
                Icon(Icons.Default.Speed, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                Text(
                  text = "سرعت پیش‌فرض پخش صدا",
                  fontSize = 16.sp,
                  fontWeight = FontWeight.Bold
                )
              }
              Text(
                text = "%.2fx".format(preferences.speechRate),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
              )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
              text = "برای مبتدی‌ها سرعت ۰.۷۵x یا ۰.۸۵x برای تشخیص دقیق پسوندها و کلمات متصل توصیه می‌شود.",
              fontSize = 13.sp,
              color = MaterialTheme.colorScheme.onSurfaceVariant,
              lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Slider(
              value = preferences.speechRate,
              onValueChange = { newRate ->
                val rounded = (newRate * 20).roundToInt() / 20f
                viewModel.updateSpeedPreference(rounded)
              },
              valueRange = 0.5f..1.5f,
              steps = 9,
              modifier = Modifier
                .fillMaxWidth()
                .testTag("speech_rate_slider")
            )

            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween
            ) {
              Text("0.5x (بسیار آرام)", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
              Text("1.0x (طبیعی)", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
              Text("1.5x (سریع)", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
          }
        }
      }

      // Test Audio Button
      item {
        Button(
          onClick = {
            viewModel.playAudio(
              "Hello! Welcome to GrammarVoice. Listening is the key to learning English grammar naturally.",
              "settings_test_audio"
            )
          },
          modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .testTag("test_audio_button"),
          shape = RoundedCornerShape(14.dp)
        ) {
          Icon(Icons.Default.VolumeUp, contentDescription = null)
          Spacer(modifier = Modifier.size(8.dp))
          Text("تست و شنیدن نمونه صدا", fontSize = 15.sp, fontWeight = FontWeight.Bold)
        }
      }

      // About
      item {
        Card(
          shape = RoundedCornerShape(16.dp),
          colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)),
          modifier = Modifier.fillMaxWidth()
        ) {
          Column(modifier = Modifier.padding(16.dp)) {
            Text(
              text = "درباره اپلیکیشن GrammarVoice",
              fontSize = 14.sp,
              fontWeight = FontWeight.Bold,
              color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
              text = "طراحی شده برای تقویت همزمان گرامر و مهارت شنیداری (Listening) زبان‌آموزان مبتدی با روش تمایز صداها و تلفظ طبیعی کلمات انگلیسی.",
              fontSize = 12.sp,
              color = MaterialTheme.colorScheme.onSurfaceVariant,
              lineHeight = 18.sp
            )
          }
        }
      }
    }
  }
}
