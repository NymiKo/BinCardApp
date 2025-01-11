package com.example.bincardapp.features.bin_lookup.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bincardapp.R
import com.example.bincardapp.core.models.TextField
import com.example.bincardapp.data.NetworkResult
import com.example.bincardapp.features.bin_lookup.domain.BinLookupRepository
import com.example.bincardapp.features.bin_lookup.domain.model.BinInfoModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BinLookupViewModel @Inject constructor(
    private val binLookupRepository: BinLookupRepository,
) : ViewModel() {
    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<Int?>(null)
        private set

    var binInfo by mutableStateOf<BinInfoModel?>(null)
        private set

    var binTextFieldState by mutableStateOf<TextField>(TextField())
        private set

    fun onEvent(event: BinLookupScreenEvents) {
        when (event) {
            BinLookupScreenEvents.GetBinInfo -> getBinInfo()
            is BinLookupScreenEvents.BinValueChanged -> {
                binTextFieldState = binTextFieldState.copy(value = event.newValue, isError = false)
                if (event.newValue.isEmpty()) binInfo = null
            }
        }
    }

    private fun getBinInfo() = viewModelScope.launch {
        if (checkBinValue()) {
            isLoading = true
            binInfo = null
            when (val result = binLookupRepository.getBinInfo(binTextFieldState.value)) {
                is NetworkResult.Error -> {
                    errorMessage = errorHandler(result.code)
                    isLoading = false
                }

                is NetworkResult.Success -> {
                    binInfo = result.data
                    errorMessage = null
                    isLoading = false
                }
            }
        }
    }

    private fun checkBinValue(): Boolean {
        when {
            binTextFieldState.value.isEmpty() -> {
                binTextFieldState = binTextFieldState.copy(
                    isError = true,
                    errorMessage = R.string.error_empty_field
                )
                return false
            }

            binTextFieldState.value.length < MIN_BIN_LENGTH -> {
                binTextFieldState =
                    binTextFieldState.copy(isError = true, errorMessage = R.string.error_min_length)
                return false
            }

            else -> return true
        }
    }

    private fun errorHandler(code: Int): Int? {
        return when (code) {
            105 -> R.string.error_no_internet
            404 -> R.string.error_not_found
            429 -> R.string.error_request_limit
            else -> null
        }
    }

    companion object {
        const val MIN_BIN_LENGTH = 6
    }
}