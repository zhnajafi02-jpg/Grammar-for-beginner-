package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Badge
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.model.GrammarUnit
import com.example.ui.components.StarRatingRow
import com.example.ui.theme.AccentAmber
import com.example.ui.theme.AccentGreen
import com.example.ui.theme.PrimaryBlue
import com.example.ui.theme.SecondaryTeal
import com.example.ui.viewmodel.GrammarViewModel
import com.example.ui.viewmodel.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
  viewModel: GrammarViewModel,
  modifier: Modifier = Modifier
) {
  val units = viewModel.units
  val progressMap by viewModel.progressMap.collectAsState()
  val preferences by viewModel.preferences.collectAsState()
  val bookmarks by viewModel.bookmarks.collectAsState()

  val totalCompleted = progressMap.count { it.value.isCompleted }
  val totalStars = progressMap.values.sumOf { it.stars }

  Scaffold(
    topBar = {
      TopAppBar(
        title = {
          Column {
            Text(
              text = "GrammarVoice",
              fontWeight = FontWeight.Bold,
              fontSize = 20.sp,
              color = MaterialTheme.colorScheme.onSurface
            )
            Text(
              text = "آموزش گرامر مبتدی با صوت و لیسنینگ",
              fontSize = 12.sp,
              color = MaterialTheme.colorScheme.onSurfaceVariant
            )
          }
        },
        actions = {
          IconButton(
            onClick = { viewModel.navigateTo(Screen.Bookmarks) },
            modifier = Modifier.testTag("nav_bookmarks_button")
          ) {
            Box {
              Icon(
                Icons.Default.Bookmark,
                contentDescription = "نشان‌شده‌ها",
                tint = MaterialTheme.colorScheme.primary
              )
              if (bookmarks.isNotEmpty()) {
                Box(
                  modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(AccentAmber)
                    .align(Alignment.TopEnd)
                )
              }
            }
          }
          IconButton(
            onClick = { viewModel.navigateTo(Screen.Settings) },
            modifier = Modifier.testTag("nav_settings_button")
          ) {
            Icon(
              Icons.Default.Settings,
              contentDescription = "تنظیمات صوت",
              tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
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
      contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
      verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
      // Hero Banner
      item {
        Card(
          shape = RoundedCornerShape(20.dp),
          modifier = Modifier
            .fillMaxWidth()
            .testTag("home_hero_banner"),
          elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
        ) {
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .height(180.dp)
          ) {
            Image(
              painter = painterResource(id = R.drawable.hero_listening),
              contentDescription = "تقویت گرامر با صوت",
              modifier = Modifier.fillMaxSize(),
              contentScale = ContentScale.Crop
            )
            Box(
              modifier = Modifier
                .fillMaxSize()
                .background(
                  Brush.verticalGradient(
                    colors = listOf(
                      Color.Transparent,
                      Color.Black.copy(alpha = 0.85f)
                    ),
                    startY = 60f
                  )
                )
            )
            Column(
              modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
            ) {
              Text(
                text = "گرامر را با گوش‌هایت یاد بگیر!",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
              )
              Text(
                text = "آموزش قواعد همراه با تمایز شنیداری و تلفظ طبیعی کلمات",
                color = Color.White.copy(alpha = 0.85f),
                fontSize = 13.sp
              )
            }
          }
        }
      }

      // Stats Bar: Streak, Completed, Stars
      item {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          StatCard(
            icon = Icons.Default.LocalFireDepartment,
            iconTint = AccentAmber,
            value = "${preferences.streakDays} روز",
            label = "پیوستگی مطالعه",
            modifier = Modifier.weight(1f)
          )
          StatCard(
            icon = Icons.Default.CheckCircle,
            iconTint = AccentGreen,
            value = "$totalCompleted از ${units.size}",
            label = "درس تکمیل شده",
            modifier = Modifier.weight(1f)
          )
          StatCard(
            icon = Icons.Default.Star,
            iconTint = AccentAmber,
            value = "$totalStars",
            label = "ستاره‌های کسب شده",
            modifier = Modifier.weight(1f)
          )
        }
      }

      // Quick Hub Banner: Listening Ear Training Lab
      item {
        Card(
          onClick = { viewModel.navigateTo(Screen.ListeningHub) },
          shape = RoundedCornerShape(16.dp),
          colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
          ),
          modifier = Modifier
            .fillMaxWidth()
            .testTag("action_listening_hub")
        ) {
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(14.dp),
              modifier = Modifier.weight(1f)
            ) {
              Box(
                modifier = Modifier
                  .size(46.dp)
                  .clip(CircleShape)
                  .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
              ) {
                Icon(
                  Icons.Default.Headphones,
                  contentDescription = null,
                  tint = MaterialTheme.colorScheme.onPrimary,
                  modifier = Modifier.size(26.dp)
                )
              }
              Column {
                Text(
                  text = "آزمایشگاه تمایز شنیداری",
                  fontSize = 16.sp,
                  fontWeight = FontWeight.Bold,
                  color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Text(
                  text = "تمرین تفاوت Can و Can't، پسوند s و گذشته ed",
                  fontSize = 12.sp,
                  color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                )
              }
            }
            Icon(
              Icons.AutoMirrored.Filled.ArrowForward,
              contentDescription = "ورود به آزمایشگاه شنیداری",
              tint = MaterialTheme.colorScheme.primary
            )
          }
        }
      }

      // Header for Units
      item {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(
            text = "فصل‌های آموزشی گرامر مبتدی",
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
          )
          Text(
            text = "${units.size} درس کامل",
            fontSize = 13.sp,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Medium
          )
        }
      }

      // Units List
      items(units, key = { it.id }) { unit ->
        val progress = progressMap[unit.id]
        UnitCard(
          unit = unit,
          isCompleted = progress?.isCompleted == true,
          stars = progress?.stars ?: 0,
          onOpenUnit = { viewModel.navigateTo(Screen.UnitDetail(unit.id)) },
          onStartExercise = { viewModel.startExerciseSession(unit.id) }
        )
      }

      item {
        Spacer(modifier = Modifier.height(24.dp))
      }
    }
  }
}

@Composable
fun StatCard(
  icon: androidx.compose.ui.graphics.vector.ImageVector,
  iconTint: Color,
  value: String,
  label: String,
  modifier: Modifier = Modifier
) {
  Card(
    modifier = modifier,
    shape = RoundedCornerShape(14.dp),
    colors = CardDefaults.cardColors(
      containerColor = MaterialTheme.colorScheme.surface
    ),
    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center
    ) {
      Icon(
        imageVector = icon,
        contentDescription = null,
        tint = iconTint,
        modifier = Modifier.size(24.dp)
      )
      Spacer(modifier = Modifier.height(4.dp))
      Text(
        text = value,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onSurface
      )
      Text(
        text = label,
        fontSize = 11.sp,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
      )
    }
  }
}

@Composable
fun UnitCard(
  unit: GrammarUnit,
  isCompleted: Boolean,
  stars: Int,
  onOpenUnit: () -> Unit,
  onStartExercise: () -> Unit,
  modifier: Modifier = Modifier
) {
  Card(
    onClick = onOpenUnit,
    modifier = modifier
      .fillMaxWidth()
      .testTag("unit_card_${unit.id}"),
    shape = RoundedCornerShape(18.dp),
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
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          Box(
            modifier = Modifier
              .size(34.dp)
              .clip(CircleShape)
              .background(
                if (isCompleted) AccentGreen.copy(alpha = 0.15f)
                else MaterialTheme.colorScheme.primaryContainer
              ),
            contentAlignment = Alignment.Center
          ) {
            Text(
              text = "${unit.number}",
              fontSize = 15.sp,
              fontWeight = FontWeight.Bold,
              color = if (isCompleted) AccentGreen else MaterialTheme.colorScheme.primary
            )
          }

          Surface(
            shape = RoundedCornerShape(8.dp),
            color = MaterialTheme.colorScheme.surfaceVariant
          ) {
            Text(
              text = unit.level,
              fontSize = 11.sp,
              fontWeight = FontWeight.Medium,
              color = MaterialTheme.colorScheme.onSurfaceVariant,
              modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
            )
          }
        }

        StarRatingRow(stars = stars, starSize = 16)
      }

      Spacer(modifier = Modifier.height(10.dp))

      Text(
        text = unit.titleFa,
        fontSize = 17.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onSurface
      )

      Text(
        text = unit.titleEn,
        fontSize = 13.sp,
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.Medium
      )

      Spacer(modifier = Modifier.height(6.dp))

      Text(
        text = unit.summaryFa,
        fontSize = 13.sp,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        lineHeight = 18.sp
      )

      Spacer(modifier = Modifier.height(12.dp))

      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Button(
          onClick = onOpenUnit,
          modifier = Modifier
            .weight(1f)
            .height(40.dp)
            .testTag("learn_unit_button_${unit.id}"),
          colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary
          ),
          shape = RoundedCornerShape(10.dp)
        ) {
          Text(
            text = "آموزش و صوت‌ها",
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold
          )
        }

        FilledTonalButton(
          onClick = onStartExercise,
          modifier = Modifier
            .weight(1f)
            .height(40.dp)
            .testTag("exercise_unit_button_${unit.id}"),
          shape = RoundedCornerShape(10.dp)
        ) {
          Icon(
            Icons.Default.Headphones,
            contentDescription = null,
            modifier = Modifier.size(16.dp)
          )
          Spacer(modifier = Modifier.width(4.dp))
          Text(
            text = "تمرین شنیداری",
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold
          )
        }
      }
    }
  }
}
