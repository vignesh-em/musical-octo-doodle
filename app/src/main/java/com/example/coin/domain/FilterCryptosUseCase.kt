package com.example.coin.domain

import com.example.coin.data.models.Crypto
import javax.inject.Inject

class FilterCryptosUseCase @Inject constructor() {
    operator fun invoke(cryptos: List<Crypto>, filters: List<Filter>) =
        cryptos.filter { crypto ->
            filters.all { filter -> filter(crypto) }
        }
}

typealias Filter = (Crypto) -> Boolean