package com.example.coin.domain

import com.example.coin.createCrypto
import com.example.coin.data.models.Crypto
import com.example.coin.data.models.Type
import junit.framework.TestCase.assertEquals
import org.junit.Test

class SearchCryptosUseCaseTest {

    @Test
    fun `cryptos with matching names are returned`() {
        val useCase = SearchCryptosUseCase()
        val bitcoin = createCrypto(
            name = "Bitcoin",
            symbol = "BTC",
            isNew = false,
            isActive = true,
            type = Type.Coin
        )
        val results = useCase(
            listOf(
                bitcoin,
                createCrypto(
                    name = "Ethereum",
                    symbol = "ETH",
                )
            ), "bit"
        )
        assertEquals(1, results.size)
        assertEquals(bitcoin, results.first())
    }

    @Test
    fun `cryptos with matching symbols are returned`() {
        val useCase = SearchCryptosUseCase()
        val ethereum = createCrypto(
            name = "Another coin",
            symbol = "ETH",
            isNew = false,
            isActive = true,
            type = Type.Coin
        )
        val results = useCase(
            listOf(
                createCrypto(
                    name = "Bitcoin",
                    symbol = "BTC",
                ),
                ethereum
            ), "ETH"
        )
        assertEquals(1, results.size)
        assertEquals(ethereum, results.first())
    }
}