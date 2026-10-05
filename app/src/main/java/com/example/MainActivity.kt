package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.screens.BookmarksScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.LessonDetailScreen
import com.example.ui.screens.ListeningExerciseScreen
import com.example.ui.screens.ListeningHubScreen
import com.example.ui.screens.SettingsScreen
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.viewmodel.GrammarViewModel
import com.example.ui.viewmodel.Screen

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      MyApplicationTheme {
        Surface(
          modifier = Modifier.fillMaxSize(),
          color = MaterialTheme.colorScheme.background
        ) {
          GrammarApp()
        }
      }
    }
  }
}

@Composable
fun GrammarApp(
  viewModel: GrammarViewModel = viewModel()
) {
  val currentScreen by viewModel.currentScreen.collectAsState()

  // Standard RTL composition provider for Persian UI readability
  CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
    AnimatedContent(
      targetState = currentScreen,
      transitionSpec = {
        fadeIn() togetherWith fadeOut()
      },
      label = "screen_transition"
    ) { screen ->
      when (screen) {
        is Screen.Home -> {
          HomeScreen(viewModel = viewModel)
        }
        is Screen.UnitDetail -> {
          LessonDetailScreen(unitId = screen.unitId, viewModel = viewModel)
        }
        is Screen.ExerciseSession -> {
          ListeningExerciseScreen(unitId = screen.unitId, viewModel = viewModel)
        }
        is Screen.ListeningHub -> {
          ListeningHubScreen(viewModel = viewModel)
        }
        is Screen.Bookmarks -> {
          BookmarksScreen(viewModel = viewModel)
        }
        is Screen.Settings -> {
          SettingsScreen(viewModel = viewModel)
        }
      }
    }
  }
}
