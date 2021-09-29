package me.igorfedorov.androidapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.igorfedorov.androidapp.other.Constants.START_LOADING
import me.igorfedorov.androidapp.other.Constants.STOP_LOADING
import me.igorfedorov.androidapp.other.LoadingState

class MainActivityViewModel : ViewModel() {

    private val _loadingState = MutableLiveData(LoadingState.WAITING)
    val loadingState: LiveData<LoadingState>
        get() = _loadingState

    private val _buttonText = MutableLiveData(START_LOADING)
    val buttonText: LiveData<String>
        get() = _buttonText


    fun updateLoadingState() {
        when (_loadingState.value) {
            LoadingState.WAITING -> {
                viewModelScope.launch {
                    updateButtonText(START_LOADING)
                    _loadingState.postValue(LoadingState.LOADING)
                    try {
                        delay(5000)
                        _loadingState.postValue(LoadingState.WAITING)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    } finally {
                        _loadingState.postValue(LoadingState.WAITING)
                    }
                }
            }
            LoadingState.LOADING -> {
                viewModelScope.launch {
                    updateButtonText(STOP_LOADING)
                    _loadingState.postValue(LoadingState.WAITING)
                }
            }
        }
    }

    private fun updateButtonText(text: String) {
        _buttonText.postValue(text)
    }

}