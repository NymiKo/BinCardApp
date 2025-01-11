package com.example.bincardapp.features.bin_lookup.data.repository

import com.example.bincardapp.data.NetworkResult
import com.example.bincardapp.features.bin_lookup.data.api.BinLookupApi
import com.example.bincardapp.features.bin_lookup.domain.BinLookupRepository
import com.example.bincardapp.features.bin_lookup.domain.model.BankInfoModel
import com.example.bincardapp.features.bin_lookup.domain.model.BinInfoModel
import com.example.bincardapp.features.bin_lookup.domain.model.CountryInfoModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BinLookupRepositoryImpl @Inject constructor(
    private val binLookupApi: BinLookupApi,
) : BinLookupRepository {
    override suspend fun getBinInfo(bin: String): NetworkResult<BinInfoModel> =
        withContext(Dispatchers.IO) {
            delay(3000)
            return@withContext NetworkResult.Success(
                BinInfoModel(
                    countryInfo = CountryInfoModel(
                        name = "\uD83C\uDDE9\uD83C\uDDF0 Denmark",
                        latitude = 56,
                        longitude = 10,
                    ),
                    cardType = "Visa",
                    bankInfo = BankInfoModel(
                        name = "Jyske Bank",
                        url = "www.jyskebank.dk",
                        phone = "+4589893300",
                        city = "Hjørring",
                    )
                )
            )
//            when(val result = handleApi { binLookupApi.getBinInfo(bin) }) {
//                is NetworkResult.Error -> NetworkResult.Error(result.code)
//                is NetworkResult.Success -> NetworkResult.Success(result.data.toBinInfoModel())
//            }
        }
}