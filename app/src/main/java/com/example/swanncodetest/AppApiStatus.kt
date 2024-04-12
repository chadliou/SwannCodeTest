package com.example.swanncodetest

/**
 * Created by chad on 12/4/2024
 */
data class AppApiStatus<out T>(
    val status: ConnectStatus,
    val data: T?,
    val message: String?
) {

    companion object {
        fun <T> success(data: T?): AppApiStatus<T> {
            return AppApiStatus(ConnectStatus.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T): AppApiStatus<T> {
            return AppApiStatus(ConnectStatus.ERROR, data, msg)
        }

        fun <T> loading(data: T?): AppApiStatus<T> {
            return AppApiStatus(ConnectStatus.LOADING, data, null)
        }
    }
}

enum class ConnectStatus {
    SUCCESS,
    ERROR,
    LOADING
}