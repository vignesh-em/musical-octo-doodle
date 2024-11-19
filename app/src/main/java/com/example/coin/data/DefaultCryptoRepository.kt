package com.example.coin.data

import com.example.coin.data.models.toCrypto
import com.example.coin.data.models.toEntity
import com.example.coin.data.source.database.CryptoDao
import com.example.coin.data.source.network.CryptoNetworkDataSource
import javax.inject.Inject
import kotlinx.coroutines.flow.flow

class DefaultCryptoRepository @Inject constructor(
    private val cryptoDao: CryptoDao,
    private val networkDataSource: CryptoNetworkDataSource,
) : CryptoRepository {

    override suspend fun getCryptos() = flow {
        val coins = cryptoDao
            .getCryptos()
            .map { crypto -> crypto.toCrypto() }

        if (coins.isNotEmpty()) {
            emit(coins)
            return@flow
        }

        val coinsFromNetwork = networkDataSource
            .getCrypto()
            .map { crypto -> crypto.toCrypto() }

        cryptoDao.insertCoins(
            coinsFromNetwork.map { crypto -> crypto.toEntity() }
        )

        emit(coinsFromNetwork)
    }
}