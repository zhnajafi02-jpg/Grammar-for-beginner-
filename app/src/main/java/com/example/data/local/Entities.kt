package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_progress")
data class UserProgressEntity(
  @PrimaryKey val unitId: String,
  val isCompleted: Boolean = false,
  val score: Int = 0,
  val stars: Int = 0,
  val completedAt: Long = 0L
)

@Entity(tableName = "bookmarks")
data class BookmarkEntity(
  @PrimaryKey(autoGenerate = true) val id: Long = 0L,
  val englishText: String,
  val persianText: String,
  val grammarNote: String = "",
  val unitId: String = "",
  val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "user_preferences")
data class UserPreferencesEntity(
  @PrimaryKey val id: Int = 1,
  val streakDays: Int = 1,
  val lastStudyDateMillis: Long = System.currentTimeMillis(),
  val speechRate: Float = 1.0f,
  val accent: String = "US",
  val totalAudioMinutes: Int = 0
)
