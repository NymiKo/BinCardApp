package com.example.bincardapp.data

sealed class NetworkResult<out T> {
    data class Success<out T>(val data: T) : NetworkResult<T>()
    data class Error(val code: Int) : NetworkResult<Nothing>()
}
