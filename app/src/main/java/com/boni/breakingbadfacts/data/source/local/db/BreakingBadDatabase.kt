package com.boni.breakingbadfacts.data.source.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.boni.breakingbadfacts.data.source.local.converters.Converters
import com.boni.breakingbadfacts.data.source.local.dao.BreakingBadDao
import com.boni.breakingbadfacts.data.source.local.model.CharacterEntity
import com.boni.breakingbadfacts.data.source.local.model.EpisodeEntity
import com.boni.breakingbadfacts.data.source.local.model.QuoteEntity

@Database(entities = [EpisodeEntity::class, CharacterEntity::class, QuoteEntity::class], version = 3)
@TypeConverters(Converters::class)
abstract class BreakingBadDatabase : RoomDatabase() {

    abstract fun breakingBadDao(): BreakingBadDao

    companion object {
        fun buildDatabase(context: Context): BreakingBadDatabase {
            return Room.databaseBuilder(context, BreakingBadDatabase::class.java, "BreakingBadDB")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}