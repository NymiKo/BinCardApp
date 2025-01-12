package com.example.bincardapp.di

import android.content.Context
import androidx.room.Room
import com.example.bincardapp.data.local.RoomDatabaseApp
import com.example.bincardapp.data.local.contract.RoomContract
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Singleton
    fun provideRoom(@ApplicationContext context: Context): RoomDatabaseApp =
        Room.databaseBuilder(context, RoomDatabaseApp::class.java, RoomContract.database).build()

    @Provides
    @Singleton
    fun provideBinDao(database: RoomDatabaseApp) = database.binDao()
}