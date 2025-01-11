package com.example.bincardapp.features.bin_lookup.data.api.models

import com.google.gson.annotations.SerializedName

data class BinInfoDto(
    @SerializedName("country") val countryInfo: CountryInfoDto,
    @SerializedName("scheme") val cardType: String?,
    @SerializedName("bank") val bankInfo: BankInfoDto,
)
