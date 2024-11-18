package com.example.coin.data.source.network

interface CoinNetworkDataSource {
    suspend fun getCoins(): List<NetworkCypto>
}