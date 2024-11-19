package com.example.coin.data.models

import com.example.coin.data.source.database.CryptoEntity
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
    if (type == "coin") Type.Coin else Type.Token,
)

fun CryptoEntity.toCrypto() = Crypto(
    name,
    symbol,
    isNew,
    isActive,
    type,
)

fun Crypto.toEntity() = CryptoEntity(
    name,
    symbol,
    isNew,
    isActive,
    type,
)

fun Crypto.isCoin() = type == Type.Coin