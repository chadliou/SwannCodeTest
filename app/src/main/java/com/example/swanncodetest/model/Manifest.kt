package com.example.swanncodetest.model

import com.google.gson.annotations.SerializedName

/**
 * Created by chad on 12/4/2024
 */
data class Manifest(
    @SerializedName("url1") val url1: String,
    @SerializedName("url2") val url2: String,
    @SerializedName("url3") val url3: String
)