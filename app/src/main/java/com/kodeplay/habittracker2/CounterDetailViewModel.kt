package com.kodeplay.habittracker2

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.marsphotos.network.CounterApi
import com.kodeplay.habittracker2.database.CounterDatabase
import com.kodeplay.habittracker2.database.CounterDatabaseDao
import com.kodeplay.habittracker2.database.CounterDatabaseEntity
import kotlinx.coroutines.launch

class CounterDetailViewModel(var application: Application):ViewModel() {
    private val _counterData = MutableLiveData<Int>()
    private val _counterDatabaseData = MutableLiveData<Int>()
    private val db:CounterDatabase = CounterDatabase.getInstance(application.applicationContext)


    val counterData:LiveData<Int>
    get () = _counterData

    val counterDatabaseData:LiveData<Int>
    get () = _counterDatabaseData


    private val _status = MutableLiveData <String>()
    val status:LiveData<String> = _status
init{
    _counterData.value = 0
    getMarsPhotos ()
    getLocalDatabaseCount ()
}
    fun updateCount (){
        _counterData.value = _counterData.value?.plus(1)
        _counterData.value?.let { updateLocalDatabaseCount (it) }
    }
    private fun getLocalDatabaseCount(){
        viewModelScope.launch {
            val counterTableRow = db.counterDatabaseDao.getCounterdata()
            _counterDatabaseData.value = counterTableRow?.counterData
        }
    }
    private fun updateLocalDatabaseCount (cnt:Int){
        viewModelScope.launch {
            val counterTableRow = CounterDatabaseEntity (1,cnt)
            db.counterDatabaseDao.update(counterTableRow)
            getLocalDatabaseCount ()
        }
        }



    private fun getMarsPhotos() {
        viewModelScope.launch {
            try {
                val counterDataObject = CounterApi.retrofitService.getPhotos()
                _status.value = counterDataObject.counterData.toString()
            }
            catch (e: Exception)
            {
                _status.value = "Failure: ${e.message}"
            }
        }
    }
}