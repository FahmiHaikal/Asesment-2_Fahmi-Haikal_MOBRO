package com.fahmi0003.asesmen2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fahmi0003.asesmen2.model.Minuman

@Database(entities = [Minuman::class], version = 1, exportSchema = false)
abstract class MinumanDb : RoomDatabase() {

    abstract val dao: MinumanDao

    companion object {

        @Volatile
        private var INSTANCE: MinumanDb? = null

        fun getInstance(context: Context): MinumanDb {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MinumanDb::class.java,
                        "minuman.db"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}