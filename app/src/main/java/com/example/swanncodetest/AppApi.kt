package com.example.swanncodetest

import com.example.swanncodetest.model.responds.VideoUrlResponse
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by chad on 12/4/2024
 */
interface AppApi {

    @GET("https://66anmbvqtuyabd7dpvy5vwqp2e0drbfz.lambda-url.ap-southeast-1.on.aws")
    suspend fun getVideoUrl(): Response<VideoUrlResponse>
}