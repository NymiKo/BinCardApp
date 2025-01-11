package com.example.bincardapp.features.bin_lookup.domain

import com.example.bincardapp.data.NetworkResult
import com.example.bincardapp.features.bin_lookup.domain.model.BinInfoModel

interface BinLookupRepository {
    suspend fun getBinInfo(bin: String): NetworkResult<BinInfoModel>
}