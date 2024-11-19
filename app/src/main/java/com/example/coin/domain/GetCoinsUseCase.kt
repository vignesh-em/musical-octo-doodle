package com.example.coin.domain

import com.example.coin.data.CoinRepository
import com.example.coin.data.models.Crypto
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetCoinsUseCase @Inject constructor(private val coinRepository: CoinRepository) {
    suspend operator fun invoke(): Flow<List<Crypto>> {
        return coinRepository.getCoins()
            .map { cryptos -> cryptos.sortedBy { crypto -> crypto.name } }
    }
}