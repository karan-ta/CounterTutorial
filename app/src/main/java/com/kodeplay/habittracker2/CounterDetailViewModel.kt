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
import com.kodeplay.habittracker2.repository.CounterRepository
import kotlinx.coroutines.launch

class CounterDetailViewModel(var application: Application):ViewModel() {
    private val db:CounterDatabase = CounterDatabase.getInstance(application.applicationContext)
    private val counterRepository = CounterRepository(db)
    private val _counterData = MutableLiveData<Int>()
    val counterData: LiveData<Int>
        get () = _counterData

//    private val _status = MutableLiveData <String>()
//    val status:LiveData<String> = _status
init{
//    getMarsPhotos ()
    loadCounterDataFromRepository ()
}
    fun updateCount (){
        _counterData.value = _counterData.value?.plus(1)
        viewModelScope.launch {
            counterRepository.updateCounterDataInRepository (_counterData.value!!)
        }
        }

    private fun loadCounterDataFromRepository(){
        viewModelScope.launch {
            _counterData.value  = counterRepository.loadCounterData()
        }
    }

//    private fun getMarsPhotos() {
//        viewModelScope.launch {
//            try {
//                val counterDataObject = CounterApi.retrofitService.getPhotos()
//                _status.value = counterDataObject.counterData.toString()
//            }
//            catch (e: Exception)
//            {
//                _status.value = "Failure: ${e.message}"
//            }
//        }
//    }
}