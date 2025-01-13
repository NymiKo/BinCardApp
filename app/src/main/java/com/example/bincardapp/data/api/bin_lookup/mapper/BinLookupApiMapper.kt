package com.example.bincardapp.data.api.bin_lookup.mapper

import com.example.bincardapp.data.api.bin_lookup.models.BankInfoDto
import com.example.bincardapp.data.api.bin_lookup.models.BinInfoDto
import com.example.bincardapp.data.api.bin_lookup.models.CountryInfoDto
import com.example.bincardapp.features.bin_lookup.domain.model.BankInfoModel
import com.example.bincardapp.features.bin_lookup.domain.model.BinInfoModel
import com.example.bincardapp.features.bin_lookup.domain.model.CountryInfoModel

fun BinInfoDto.toBinInfoModel(bin: String) = BinInfoModel(
    bin, countryInfo.toCountryInfoModel(), cardType ?: "-", bankInfo.toBankInfoModel()
)

fun BankInfoDto.toBankInfoModel() = BankInfoModel(
    name ?: "-", url ?: "-", phone ?: "-", city ?: "-"
)

fun CountryInfoDto.toCountryInfoModel() = CountryInfoModel(
    name ?: "-", latitude ?: 0.0, longitude ?: 0.0
)