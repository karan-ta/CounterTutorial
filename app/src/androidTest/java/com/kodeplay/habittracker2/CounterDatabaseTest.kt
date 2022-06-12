package com.kodeplay.habittracker2

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kodeplay.habittracker2.database.CounterDatabase
import com.kodeplay.habittracker2.database.CounterDatabaseDao
import com.kodeplay.habittracker2.database.CounterDatabaseEntity
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import java.io.IOException

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class CounterDatabaseTest {

    private lateinit var counterDao: CounterDatabaseDao
    private lateinit var db: CounterDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, CounterDatabase::class.java)
                // Allowing main thread queries, just for testing.
                .allowMainThreadQueries()
                .build()
        counterDao = db.sleepDatabaseDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetNight() {
        val counterDataRow = CounterDatabaseEntity()
        counterDao.insert(counterDataRow)
        val counterData = counterDao.getCounterdata()
        assertEquals(counterData?.counterData, -1)
    }
}