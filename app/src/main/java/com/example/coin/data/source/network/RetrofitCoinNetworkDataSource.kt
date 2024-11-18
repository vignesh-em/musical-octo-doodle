package com.example.coin.data.source.network

import javax.inject.Inject

class RetrofitCoinNetworkDataSource @Inject constructor(private val api: CryptoApi) :
    CoinNetworkDataSource {

    override suspend fun getCoins(): List<NetworkCypto> {
        return api.getCoins()
    }
}