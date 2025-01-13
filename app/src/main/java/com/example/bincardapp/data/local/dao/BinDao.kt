package com.example.bincardapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.bincardapp.data.local.contract.RoomContract
import com.example.bincardapp.data.local.entity.BinInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BinDao {
    @Query("SELECT * FROM ${RoomContract.tableBinInfo} ORDER BY lastAccess DESC")
    fun getBinList(): Flow<List<BinInfoEntity>>

    @Query("SELECT bin FROM ${RoomContract.tableBinInfo} WHERE bin LIKE :bin")
    suspend fun getBinInfoByBin(bin: String): String?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBinInfo(binInfoEntity: BinInfoEntity)

    @Update
    suspend fun updateBinInfo(binInfoEntity: BinInfoEntity)
}