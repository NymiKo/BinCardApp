package com.example.bincardapp.features.bin_lookup.data.api.models

import com.google.gson.annotations.SerializedName

data class BankInfoDto(
    @SerializedName("name") val name: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("phone") val phone: String?,
    @SerializedName("city") val city: String?,
)
