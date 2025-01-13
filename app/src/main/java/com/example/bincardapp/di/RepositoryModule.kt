package com.example.bincardapp.di

import com.example.bincardapp.features.bin_history.data.BinHistoryRepositoryImpl
import com.example.bincardapp.features.bin_history.domain.BinHistoryRepository
import com.example.bincardapp.features.bin_lookup.data.BinLookupRepositoryImpl
import com.example.bincardapp.features.bin_lookup.domain.BinLookupRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @Singleton
    fun bindBinLookupRepository(binLookupRepositoryImpl: BinLookupRepositoryImpl): BinLookupRepository

    @Binds
    @Singleton
    fun bindBinHistoryRepository(binHistoryRepositoryImpl: BinHistoryRepositoryImpl): BinHistoryRepository
}