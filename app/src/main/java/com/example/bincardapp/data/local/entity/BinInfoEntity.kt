package com.example.bincardapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bincardapp.data.local.contract.RoomContract

@Entity(tableName = RoomContract.tableBinInfo)
data class BinInfoEntity(
    @PrimaryKey(autoGenerate = false)
    val bin: String,
    val nameCounty: String,
    val latitude: Int,
    val longitude: Int,
    val cardType: String,
    val nameBank: String,
    val url: String,
    val phone: String,
    val city: String,
    val lastAccess: Long,
)
