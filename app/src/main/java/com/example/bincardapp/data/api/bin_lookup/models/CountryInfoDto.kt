package com.example.bincardapp.data.api.bin_lookup.models

import com.google.gson.annotations.SerializedName

data class CountryInfoDto(
    @SerializedName("name") val name: String?,
    @SerializedName("latitude") val latitude: Int?,
    @SerializedName("longitude") val longitude: Int?,
)
