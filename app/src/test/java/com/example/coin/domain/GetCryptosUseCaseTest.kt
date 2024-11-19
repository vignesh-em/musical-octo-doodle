package com.example.coin.domain

import com.example.coin.data.CryptoRepository
import com.example.coin.data.models.Crypto
import com.example.coin.data.models.Type
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetCryptosUseCaseTest {
    @Test
    fun `crypto list comes in sorted order`() = runTest {
        val repo = CryptoRepository {
            flow {
                emit(
                    listOf(
                        Crypto(
                            name = "ETH",
                            symbol = "Ethereum",
                            isNew = false,
                            isActive = true,
                            type = Type.Token
                        ),
                        Crypto(
                            name = "BTC",
                            symbol = "Bitcoin",
                            isNew = true,
                            isActive = true,
                            type = Type.Coin
                        )
                    )
                )
            }
        }
        val useCase = GetCryptosUseCase(repo)

        val result = useCase().first()

        assert(result[0].name == "BTC")
        assert(result[1].name == "ETH")
    }
}