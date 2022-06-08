package com.kodeplay.habittracker2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.marsphotos.network.CounterApi
import kotlinx.coroutines.launch

class CounterDetailViewModel:ViewModel() {
    private val _counterData = MutableLiveData<Int>()
    val counterData:LiveData<Int>
    get () = _counterData

    private val _status = MutableLiveData <String>()
    val status:LiveData<String> = _status
init{
    _counterData.value = 0
    getMarsPhotos ()
}
    fun updateCount (){
        _counterData.value = _counterData.value?.plus(1)
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