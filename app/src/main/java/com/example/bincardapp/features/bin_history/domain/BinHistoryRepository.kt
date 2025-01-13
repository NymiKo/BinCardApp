package com.example.bincardapp.features.bin_history.domain

import com.example.bincardapp.features.bin_lookup.domain.model.BinInfoModel
import kotlinx.coroutines.flow.Flow

interface BinHistoryRepository {
    fun binHistoryList(): Flow<List<BinInfoModel>>
}