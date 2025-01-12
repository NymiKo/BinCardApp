package com.example.bincardapp.data.api.bin_lookup.models

import com.google.gson.annotations.SerializedName

data class BinInfoDto(
    @SerializedName("country") val countryInfo: CountryInfoDto,
    @SerializedName("scheme") val cardType: String?,
    @SerializedName("bank") val bankInfo: BankInfoDto,
)
