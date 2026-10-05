package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
  entities = [
    UserProgressEntity::class,
    BookmarkEntity::class,
    UserPreferencesEntity::class
  ],
  version = 1,
  exportSchema = false
)
abstract class GrammarDatabase : RoomDatabase() {
  abstract fun progressDao(): UserProgressDao

  companion object {
    @Volatile
    private var INSTANCE: GrammarDatabase? = null

    fun getDatabase(context: Context): GrammarDatabase {
      return INSTANCE ?: synchronized(this) {
        val instance = Room.databaseBuilder(
          context.applicationContext,
          GrammarDatabase::class.java,
          "grammar_audio_database"
        )
          .fallbackToDestructiveMigration()
          .build()
        INSTANCE = instance
        instance
      }
    }
  }
}
