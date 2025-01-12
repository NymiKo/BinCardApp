package com.example.bincardapp.features.bin_lookup.data.repository

import com.example.bincardapp.data.api.NetworkResult
import com.example.bincardapp.data.api.bin_lookup.BinLookupApi
import com.example.bincardapp.data.local.dao.BinDao
import com.example.bincardapp.data.local.dao.mapper.toBinInfoEntity
import com.example.bincardapp.features.bin_lookup.domain.BinLookupRepository
import com.example.bincardapp.features.bin_lookup.domain.model.BankInfoModel
import com.example.bincardapp.features.bin_lookup.domain.model.BinInfoModel
import com.example.bincardapp.features.bin_lookup.domain.model.CountryInfoModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BinLookupRepositoryImpl @Inject constructor(
    private val binLookupApi: BinLookupApi,
    private val binDao: BinDao,
) : BinLookupRepository {
    override suspend fun getBinInfo(bin: String): NetworkResult<BinInfoModel> =
        withContext(Dispatchers.IO) {
            delay(3000)
            val binInfo = BinInfoModel(
                bin = bin,
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
            if (binDao.getBinInfoByBin(bin) == null) {
                binDao.insertBinInfo(binInfo.toBinInfoEntity(bin))
            } else {
                binDao.updateBinInfo(binInfo.toBinInfoEntity(bin))
            }
            return@withContext NetworkResult.Success(binInfo)
//            when(val result = handleApi { binLookupApi.getBinInfo(bin) }) {
//                is NetworkResult.Error -> NetworkResult.Error(result.code)
//                is NetworkResult.Success -> {
//                    val binInfo = result.data.toBinInfoModel()
//                    val idBinInfoEntity = binDao.getBinInfoByBin(bin)
//                    if (idBinInfoEntity == null) {
//                        binDao.insertBinInfo(binInfo.toBinInfoEntity(bin = bin))
//                    } else {
//                        binDao.updateBinInfo(binInfo.toBinInfoEntity(idBinInfoEntity, bin))
//                    }
//                    NetworkResult.Success(binInfo)
//                }
//            }
        }
}