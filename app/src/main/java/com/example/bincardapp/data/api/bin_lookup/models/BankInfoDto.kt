package com.example.bincardapp.data.api.bin_lookup.models

import com.google.gson.annotations.SerializedName

data class BankInfoDto(
    @SerializedName("name") val name: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("phone") val phone: String?,
    @SerializedName("city") val city: String?,
)
