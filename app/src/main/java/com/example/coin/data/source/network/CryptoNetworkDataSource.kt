package com.example.coin.data.source.network

interface CryptoNetworkDataSource {
    suspend fun getCrypto(): List<NetworkCrypto>
}