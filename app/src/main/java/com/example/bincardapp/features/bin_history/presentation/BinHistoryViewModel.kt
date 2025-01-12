package com.example.bincardapp.features.bin_history.presentation

import androidx.lifecycle.ViewModel
import com.example.bincardapp.features.bin_history.domain.BinHistoryRepository
import com.example.bincardapp.features.bin_lookup.domain.model.BinInfoModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class BinHistoryViewModel @Inject constructor(
    private val binHistoryRepository: BinHistoryRepository,
) : ViewModel() {

    val binHistoryList: Flow<List<BinInfoModel>> = binHistoryRepository.binHistoryList()

}