package com.example.coin.data

import com.example.coin.data.models.toCrypto
import com.example.coin.data.models.toEntity
import com.example.coin.data.source.database.CryptoDao
import com.example.coin.data.source.network.CoinNetworkDataSource
import javax.inject.Inject
import kotlinx.coroutines.flow.flow

class DefaultCoinRepository @Inject constructor(
    private val cryptoDao: CryptoDao,
    private val networkDataSource: CoinNetworkDataSource,
) : CoinRepository {

    override suspend fun getCoins() = flow {
        val coins = cryptoDao
            .getCoins()
            .map { crypto -> crypto.toCrypto() }

        if (coins.isNotEmpty()) {
            emit(coins)
            return@flow
        }

        val coinsFromNetwork = networkDataSource
            .getCoins()
            .map { crypto -> crypto.toCrypto() }

        cryptoDao.insertCoins(
            coinsFromNetwork.map { crypto -> crypto.toEntity() }
        )

        emit(coinsFromNetwork)
    }
}