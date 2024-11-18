package com.example.coin.data

import com.example.coin.data.models.toCrypto
import com.example.coin.data.source.network.CoinNetworkDataSource
import javax.inject.Inject
import kotlinx.coroutines.flow.flow

class DefaultCoinRepository @Inject constructor(
    private val networkDataSource: CoinNetworkDataSource,
) : CoinRepository {

    override suspend fun getCoins() = flow {
        emit(
            networkDataSource
                .getCoins()
                .map { crypto -> crypto.toCrypto() }
        )
    }
}