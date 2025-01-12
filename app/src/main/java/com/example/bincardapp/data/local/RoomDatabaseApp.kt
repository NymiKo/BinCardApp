package com.example.bincardapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bincardapp.data.local.dao.BinDao
import com.example.bincardapp.data.local.entity.BinInfoEntity

@Database(entities = [BinInfoEntity::class], version = 1, exportSchema = true)
abstract class RoomDatabaseApp : RoomDatabase() {
    abstract fun binDao(): BinDao
}