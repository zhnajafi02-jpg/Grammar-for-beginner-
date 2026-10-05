package com.example.data.repository

import com.example.data.local.BookmarkEntity
import com.example.data.local.UserPreferencesEntity
import com.example.data.local.UserProgressDao
import com.example.data.local.UserProgressEntity
import com.example.data.model.GrammarData
import com.example.data.model.GrammarUnit
import com.example.data.model.MinimalPair
import kotlinx.coroutines.flow.Flow

class GrammarRepository(
  private val dao: UserProgressDao
) {
  fun getAllUnits(): List<GrammarUnit> = GrammarData.units

  fun getUnitById(id: String): GrammarUnit? = GrammarData.getUnitById(id)

  fun getMinimalPairs(): List<MinimalPair> = GrammarData.minimalPairs

  val allProgress: Flow<List<UserProgressEntity>> = dao.getAllProgress()

  val allBookmarks: Flow<List<BookmarkEntity>> = dao.getAllBookmarks()

  val userPreferences: Flow<UserPreferencesEntity?> = dao.getUserPreferences()

  suspend fun saveUnitCompletion(unitId: String, score: Int, totalQuestions: Int) {
    val percentage = if (totalQuestions > 0) (score.toFloat() / totalQuestions) * 100 else 0f
    val stars = when {
      percentage >= 90f -> 3
      percentage >= 60f -> 2
      percentage >= 30f -> 1
      else -> 0
    }
    val entity = UserProgressEntity(
      unitId = unitId,
      isCompleted = true,
      score = score,
      stars = stars,
      completedAt = System.currentTimeMillis()
    )
    dao.saveProgress(entity)
  }

  suspend fun toggleBookmark(
    englishText: String,
    persianText: String,
    grammarNote: String = "",
    unitId: String = "",
    isCurrentlyBookmarked: Boolean
  ) {
    if (isCurrentlyBookmarked) {
      dao.removeBookmarkByText(englishText)
    } else {
      dao.addBookmark(
        BookmarkEntity(
          englishText = englishText,
          persianText = persianText,
          grammarNote = grammarNote,
          unitId = unitId
        )
      )
    }
  }

  fun isBookmarked(englishText: String): Flow<Boolean> = dao.isBookmarked(englishText)

  suspend fun updatePreferences(speechRate: Float, accent: String) {
    val current = UserPreferencesEntity(
      id = 1,
      speechRate = speechRate,
      accent = accent
    )
    dao.saveUserPreferences(current)
  }
}
