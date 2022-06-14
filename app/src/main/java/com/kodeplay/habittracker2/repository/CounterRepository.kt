package com.kodeplay.habittracker2.repository
import android.util.Log
import com.example.android.marsphotos.network.CounterApi
import com.kodeplay.habittracker2.database.CounterDatabase
import com.kodeplay.habittracker2.database.CounterDatabaseEntity
import com.kodeplay.habittracker2.network.CounterApiData

class CounterRepository (private val db: CounterDatabase){
    lateinit var counterApiDataObject:CounterApiData
    var counterTableRow:CounterDatabaseEntity? = null
    suspend fun loadCounterData ():Int{
        var myCounterLocalData:Int = 0
        var myCounterNetworkData = 0
        counterTableRow = db.counterDatabaseDao.getCounterdata()
        if (counterTableRow == null) {
            db.counterDatabaseDao.insert(CounterDatabaseEntity(1, 0))
            try {
                CounterApi.retrofitService.createCount()
            }
            catch (e: Exception) {

            }
            return 0
        }
        else {
            myCounterLocalData = counterTableRow?.counterData!!

                 counterApiDataObject = CounterApi.retrofitService.getCount()
                myCounterNetworkData = counterApiDataObject.counterData.toString().toInt()

            if (myCounterLocalData != myCounterNetworkData) {

                    CounterApi.retrofitService.updateCount(counterApiDataObject.id,myCounterLocalData)

            }
        }
        return myCounterLocalData
    }

    suspend fun updateCounterDataInRepository (cnt:Int){
//        val counterTableRow = db.counterDatabaseDao.getCounterdata()

        db.counterDatabaseDao.update(CounterDatabaseEntity(counterTableRow?.id, cnt))

            CounterApi.retrofitService.updateCount(counterApiDataObject.id,cnt)


    }
}