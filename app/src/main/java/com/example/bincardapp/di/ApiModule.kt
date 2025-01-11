package com.example.bincardapp.di

import com.example.bincardapp.features.bin_lookup.data.api.BinLookupApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun provideBinLookupApi(retrofit: Retrofit): BinLookupApi =
        retrofit.create(BinLookupApi::class.java)
}