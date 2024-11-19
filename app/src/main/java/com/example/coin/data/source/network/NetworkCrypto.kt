package com.example.coin.data.source.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkCrypto(
    val name: String,
    val symbol: String,
    @SerialName("is_new") val isNew: Boolean,
    @SerialName("is_active") val isActive: Boolean,
    val type: String,
)
