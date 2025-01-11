package com.example.bincardapp.features.bin_lookup.data.api

import com.example.bincardapp.features.bin_lookup.data.api.models.BinInfoDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BinLookupApi {
    @GET("{bin}")
    suspend fun getBinInfo(@Path("bin") bin: String): Response<BinInfoDto>
}