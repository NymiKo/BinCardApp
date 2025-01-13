package com.example.bincardapp.di

import com.example.bincardapp.data.api.bin_lookup.BinLookupApi
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