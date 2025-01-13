package com.example.bincardapp.data.local.dao.mapper

import com.example.bincardapp.data.local.entity.BinInfoEntity
import com.example.bincardapp.features.bin_lookup.domain.model.BankInfoModel
import com.example.bincardapp.features.bin_lookup.domain.model.BinInfoModel
import com.example.bincardapp.features.bin_lookup.domain.model.CountryInfoModel

fun BinInfoEntity.toBinInfoModel() = BinInfoModel(
    bin = bin,
    countryInfo = CountryInfoModel(nameCounty, latitude, longitude),
    cardType = cardType,
    bankInfo = BankInfoModel(nameBank, url, phone, city)
)

fun BinInfoModel.toBinInfoEntity(bin: String) = BinInfoEntity(
    bin = bin,
    nameCounty = countryInfo.name,
    latitude = countryInfo.latitude,
    longitude = countryInfo.longitude,
    cardType = cardType,
    nameBank = bankInfo.name,
    url = bankInfo.url,
    city = bankInfo.city,
    phone = bankInfo.phone,
    lastAccess = System.currentTimeMillis(),
)