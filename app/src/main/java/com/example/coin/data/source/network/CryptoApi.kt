package com.example.coin.data.source.network

import retrofit2.http.GET

interface CryptoApi {
    @GET("/")
    suspend fun getCryptos(): List<NetworkCrypto>
}