package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserProgressDao {
  @Query("SELECT * FROM user_progress")
  fun getAllProgress(): Flow<List<UserProgressEntity>>

  @Query("SELECT * FROM user_progress WHERE unitId = :unitId")
  suspend fun getProgressForUnit(unitId: String): UserProgressEntity?

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun saveProgress(progress: UserProgressEntity)

  // Bookmarks
  @Query("SELECT * FROM bookmarks ORDER BY createdAt DESC")
  fun getAllBookmarks(): Flow<List<BookmarkEntity>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun addBookmark(bookmark: BookmarkEntity)

  @Query("DELETE FROM bookmarks WHERE englishText = :englishText")
  suspend fun removeBookmarkByText(englishText: String)

  @Query("SELECT EXISTS(SELECT 1 FROM bookmarks WHERE englishText = :englishText)")
  fun isBookmarked(englishText: String): Flow<Boolean>

  // Preferences
  @Query("SELECT * FROM user_preferences WHERE id = 1")
  fun getUserPreferences(): Flow<UserPreferencesEntity?>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun saveUserPreferences(prefs: UserPreferencesEntity)
}
