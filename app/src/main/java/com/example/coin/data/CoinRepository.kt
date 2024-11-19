package com.example.coin.data

import com.example.coin.data.models.Crypto
import kotlinx.coroutines.flow.Flow

fun interface CoinRepository {
    suspend fun getCoins(): Flow<List<Crypto>>
}