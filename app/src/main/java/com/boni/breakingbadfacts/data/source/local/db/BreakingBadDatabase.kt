package com.boni.breakingbadfacts.data.source.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.boni.breakingbadfacts.data.source.local.dao.BreakingBadDao
import com.boni.breakingbadfacts.data.source.local.model.EpisodeEntity

@Database(entities = [EpisodeEntity::class], version = 1)
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