package com.example.bincardapp.features.bin_lookup.data.api

import com.example.bincardapp.features.bin_lookup.data.api.models.BankInfoDto
import com.example.bincardapp.features.bin_lookup.data.api.models.BinInfoDto
import com.example.bincardapp.features.bin_lookup.data.api.models.CountryInfoDto
import com.example.bincardapp.features.bin_lookup.domain.model.BankInfoModel
import com.example.bincardapp.features.bin_lookup.domain.model.BinInfoModel
import com.example.bincardapp.features.bin_lookup.domain.model.CountryInfoModel

fun BinInfoDto.toBinInfoModel() = BinInfoModel(
    countryInfo.toCountryInfoModel(), cardType ?: "-", bankInfo.toBankInfoModel()
)

fun BankInfoDto.toBankInfoModel() = BankInfoModel(
    name ?: "-", url ?: "-", phone ?: "-", city ?: "-"
)

fun CountryInfoDto.toCountryInfoModel() = CountryInfoModel(
    name ?: "-", latitude ?: 0, longitude ?: 0
)