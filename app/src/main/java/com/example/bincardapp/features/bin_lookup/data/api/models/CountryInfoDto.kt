package com.example.bincardapp.features.bin_lookup.data.api.models

import com.google.gson.annotations.SerializedName

data class CountryInfoDto(
    @SerializedName("name") val name: String?,
    @SerializedName("latitude") val latitude: Int?,
    @SerializedName("longitude") val longitude: Int?,
)
