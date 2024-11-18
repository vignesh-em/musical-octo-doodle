package com.example.coin.data.models

import com.example.coin.data.source.network.NetworkCypto

data class Crypto(
    val name: String,
    val symbol: String,
    val isNew: Boolean,
    val isActive: Boolean,
    val type: Type,
)

enum class Type { Coin, Token }

fun NetworkCypto.toCrypto() = Crypto(
    name,
    symbol,
    isNew,
    isActive,
    if (type == "coin") Type.Coin else Type.Token
)