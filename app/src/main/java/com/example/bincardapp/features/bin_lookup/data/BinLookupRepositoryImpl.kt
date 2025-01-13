package com.example.bincardapp.features.bin_lookup.data

import com.example.bincardapp.data.api.NetworkResult
import com.example.bincardapp.data.api.bin_lookup.BinLookupApi
import com.example.bincardapp.data.api.bin_lookup.mapper.toBinInfoModel
import com.example.bincardapp.data.api.handleApi
import com.example.bincardapp.data.local.dao.BinDao
import com.example.bincardapp.data.local.dao.mapper.toBinInfoEntity
import com.example.bincardapp.features.bin_lookup.domain.BinLookupRepository
import com.example.bincardapp.features.bin_lookup.domain.model.BinInfoModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BinLookupRepositoryImpl @Inject constructor(
    private val binLookupApi: BinLookupApi,
    private val binDao: BinDao,
) : BinLookupRepository {
    override suspend fun getBinInfo(bin: String): NetworkResult<BinInfoModel> =
        withContext(Dispatchers.IO) {
            when (val result = handleApi { binLookupApi.getBinInfo(bin) }) {
                is NetworkResult.Error -> NetworkResult.Error(result.code)
                is NetworkResult.Success -> {
                    val binInfo = result.data.toBinInfoModel(bin)
                    if (binDao.getBinInfoByBin(bin) == null) {
                        binDao.insertBinInfo(binInfo.toBinInfoEntity(bin))
                    } else {
                        binDao.updateBinInfo(binInfo.toBinInfoEntity(bin))
                    }
                    NetworkResult.Success(binInfo)
                }
            }
        }
}