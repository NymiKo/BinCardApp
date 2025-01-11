package com.example.bincardapp.features.bin_lookup.presentation

sealed interface BinLookupScreenEvents {
    data object GetBinInfo : BinLookupScreenEvents
    class BinValueChanged(val newValue: String) : BinLookupScreenEvents
}