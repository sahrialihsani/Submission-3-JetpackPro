package com.sahrial.submission_3_jetpackpro.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sahrial.submission_3_jetpackpro.data.source.local.entity.MovEntity
import com.sahrial.submission_3_jetpackpro.data.source.local.entity.TvEntity

@Database(entities = [MovEntity::class, TvEntity::class], version = 1, exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun localDao(): LocalDao
    companion object {
        @Volatile
        private var INS: LocalDatabase? = null
        fun getInstance(context: Context): LocalDatabase =
            INS ?: synchronized(this) {
                INS ?: Room.databaseBuilder(
                    context.applicationContext,
                    LocalDatabase::class.java,"movieTv.db"
                ).build()
            }
    }
}