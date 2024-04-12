package com.example.swanncodetest.repository

import com.example.swanncodetest.AppApi
import javax.inject.Inject

/**
 * Created by chad on 12/4/2024
 */
class VideoRepository @Inject constructor(
    private val appApi: AppApi
) {
    /**
     * get Video manifest for video url
     */
    suspend fun getVideoManifest() = appApi.getVideoUrl()
}