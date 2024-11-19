package com.example.coin.domain

import com.example.coin.createCrypto
import com.example.coin.data.models.Crypto
import com.example.coin.data.models.Type
import com.example.coin.data.models.isCoin
import junit.framework.TestCase.assertEquals
import org.junit.Test

class FilterCryptosUseCaseTest {
    @Test
    fun `cryptos with matching filters are returned`() {
        val coinFilter = { crypto: Crypto -> crypto.isCoin() }
        val activeFilter = { crypto: Crypto -> crypto.isActive }

        val bitcoin =
            createCrypto(name = "Bitcoin", type = Type.Coin, isNew = false, isActive = true)
        val cryptos = listOf(
            bitcoin,
            createCrypto(type = Type.Coin, isNew = true, isActive = false),
            createCrypto(type = Type.Token, isActive = true),
            createCrypto(type = Type.Token, isActive = true),
        )

        val useCase = FilterCryptosUseCase()

        val filteredCryptos = useCase(
            cryptos,
            listOf(coinFilter, activeFilter)
        )

        assertEquals(1, filteredCryptos.size)
        assertEquals(bitcoin, filteredCryptos.first())
    }

}