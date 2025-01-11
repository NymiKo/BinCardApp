package com.example.bincardapp.features.bin_lookup.domain.model

data class BinInfoModel(
    val countryInfo: CountryInfoModel,
    val cardType: String,
    val bankInfo: BankInfoModel,
)
