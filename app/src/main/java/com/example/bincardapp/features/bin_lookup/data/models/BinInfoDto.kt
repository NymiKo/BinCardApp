package com.example.bincardapp.features.bin_lookup.data.models

data class BinInfoDto(
    val country: String,
    val latitude: Int,
    val longitude: Int,
    val cardType: String,
    val bankInfo: BankInfoDto,
)
