package com.example.bincardapp.features.bin_history.data

import com.example.bincardapp.data.local.dao.BinDao
import com.example.bincardapp.data.local.dao.mapper.toBinInfoModel
import com.example.bincardapp.features.bin_history.domain.BinHistoryRepository
import com.example.bincardapp.features.bin_lookup.domain.model.BinInfoModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BinHistoryRepositoryImpl @Inject constructor(
    private val binDao: BinDao,
) : BinHistoryRepository {
    override fun binHistoryList(): Flow<List<BinInfoModel>> {
        return binDao.getBinList().map { binInfoEntities ->
            binInfoEntities.map {
                it.toBinInfoModel()
            }
        }
    }
}