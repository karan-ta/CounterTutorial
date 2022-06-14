package com.kodeplay.habittracker2.database
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [CounterDatabaseEntity::class], version = 2, exportSchema = false)
abstract class CounterDatabase : RoomDatabase() {
    abstract val counterDatabaseDao: CounterDatabaseDao
    companion object {
        @Volatile
        private var INSTANCE: CounterDatabase? = null
        fun getInstance(context: Context): CounterDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CounterDatabase::class.java,
                        "counter_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}