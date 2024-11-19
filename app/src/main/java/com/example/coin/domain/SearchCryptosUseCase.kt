package com.example.coin.domain

import com.example.coin.data.models.Crypto
import javax.inject.Inject

class SearchCryptosUseCase @Inject constructor() {
    operator fun invoke(cryptos: List<Crypto>, query: String): List<Crypto> = buildSet {
        addAll(cryptos.filter { crypto ->
            crypto.name.contains(
                query,
                ignoreCase = true
            )
        })
        addAll(cryptos.filter { crypto ->
            crypto.symbol.contains(
                query,
                ignoreCase = true
            )
        })
    }.toList()

}