package com.example.swanncodetest.model.responds

import com.example.swanncodetest.model.Manifest
import com.google.gson.annotations.SerializedName

/**
 * Created by chad on 12/4/2024
 */
class VideoUrlResponse(
    @SerializedName("manifest") val manifest: Manifest
)