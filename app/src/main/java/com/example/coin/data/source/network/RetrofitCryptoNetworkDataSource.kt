package com.example.coin.data.source.network

import javax.inject.Inject

class RetrofitCryptoNetworkDataSource @Inject constructor(private val api: CryptoApi) :
    CryptoNetworkDataSource {

    override suspend fun getCrypto(): List<NetworkCrypto> {
        return api.getCryptos()
    }
}