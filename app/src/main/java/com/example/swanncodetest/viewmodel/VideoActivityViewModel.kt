package com.example.swanncodetest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swanncodetest.AppApiStatus
import com.example.swanncodetest.model.responds.VideoUrlResponse
import com.example.swanncodetest.repository.VideoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by chad on 12/4/2024
 */
@HiltViewModel
class VideoActivityViewModel @Inject constructor(
    private val videoRepository: VideoRepository
) : ViewModel() {

    private val _videoResponse = MutableLiveData<AppApiStatus<VideoUrlResponse?>>()
    val videoResponse: LiveData<AppApiStatus<VideoUrlResponse?>> get() = _videoResponse

    private val _showControl = MutableLiveData<Boolean>()
    val showControl: LiveData<Boolean> get() = _showControl

    init {
        getVideoUrl()
    }

    private fun getVideoUrl() = viewModelScope.launch(Dispatchers.IO) {
        try {
            _videoResponse.postValue(AppApiStatus.loading(null))
            videoRepository.getVideoManifest().let { response ->
                if (response.isSuccessful) {
                    _videoResponse.postValue(AppApiStatus.success(response.body()))
                } else {
                    _videoResponse.postValue(AppApiStatus.error(response.errorBody().toString(), null))
                }
            }
        } catch (e: Exception) {
            _videoResponse.postValue(AppApiStatus.error(e.toString(), null))
        }
    }

    fun showControlPanel() {
        if (_showControl.value == true) {
            _showControl.postValue(false)
        } else {
            _showControl.postValue(true)
        }
    }
}