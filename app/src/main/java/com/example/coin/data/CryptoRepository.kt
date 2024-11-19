package com.example.coin.data

import com.example.coin.data.models.Crypto
import kotlinx.coroutines.flow.Flow

fun interface CryptoRepository {
    suspend fun getCryptos(): Flow<List<Crypto>>
}