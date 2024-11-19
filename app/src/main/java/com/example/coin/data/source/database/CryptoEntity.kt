package com.example.coin.data.source.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.coin.data.models.Type

@Entity(tableName = "crypto")
@TypeConverters(CryptoTypeConverter::class)
data class CryptoEntity(
    val name: String,
    @PrimaryKey val symbol: String,
    val isNew: Boolean,
    val isActive: Boolean,
    val type: Type,
)