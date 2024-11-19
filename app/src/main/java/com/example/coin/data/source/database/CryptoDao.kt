package com.example.coin.data.source.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CryptoDao {

    @Query("SELECT * FROM crypto")
    suspend fun getCoins(): List<CryptoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoins(cryptos: List<CryptoEntity>)
}