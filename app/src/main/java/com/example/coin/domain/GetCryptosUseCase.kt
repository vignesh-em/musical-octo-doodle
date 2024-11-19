package com.example.coin.domain

import com.example.coin.data.CryptoRepository
import com.example.coin.data.models.Crypto
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetCryptosUseCase @Inject constructor(private val cryptoRepository: CryptoRepository) {
    suspend operator fun invoke(): Flow<List<Crypto>> {
        return cryptoRepository.getCryptos()
            .map { cryptos -> cryptos.sortedBy { crypto -> crypto.name } }
    }
}