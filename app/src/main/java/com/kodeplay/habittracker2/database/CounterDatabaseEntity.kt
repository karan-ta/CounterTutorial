package com.kodeplay.habittracker2.database
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "counter_data_table")
data class CounterDatabaseEntity(
    @PrimaryKey()
    var id: Int? = 1,
    @ColumnInfo(name = "counter_data")
    var counterData: Int = -1,
        )