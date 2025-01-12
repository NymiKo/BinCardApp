package com.example.bincardapp.data.api.bin_lookup

import com.example.bincardapp.data.api.bin_lookup.models.BinInfoDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BinLookupApi {
    @GET("{bin}")
    suspend fun getBinInfo(@Path("bin") bin: String): Response<BinInfoDto>
}