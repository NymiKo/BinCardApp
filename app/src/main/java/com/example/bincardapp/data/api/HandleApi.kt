package com.example.bincardapp.data.api

import retrofit2.Response
import java.net.UnknownHostException

suspend fun <T> handleApi(apiCall: suspend () -> Response<T>): NetworkResult<T> {
    return try {
        val response = apiCall()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            NetworkResult.Success(body)
        } else {
            NetworkResult.Error(response.code())
        }
    } catch (e: UnknownHostException) {
        NetworkResult.Error(105)
    }
}