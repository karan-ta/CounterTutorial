package com.kodeplay.habittracker2.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CounterDatabaseDao {
    @Insert
    suspend fun insert(counterDataRow: CounterDatabaseEntity)

    @Update
    suspend fun update  (counterDataRow: CounterDatabaseEntity)

    @Query("SELECT * from counter_data_table LIMIT 1")
    suspend fun getCounterdata ():CounterDatabaseEntity?
}