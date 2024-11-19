package com.example.coin

import com.example.coin.data.models.Crypto
import com.example.coin.data.models.Type

fun createCrypto(
    name: String = "",
    symbol: String = "",
    isActive: Boolean = false,
    isNew: Boolean = false,
    type: Type = Type.Coin,
) = Crypto(name, symbol, isNew, isActive, type)