package com.example.coin.data

import com.example.coin.data.source.database.CryptoDao
import com.example.coin.data.source.network.CoinNetworkDataSource
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class DefaultCoinRepositoryTest {
    @Test
    fun `when database is empty, network call is made`() = runTest {
        val cryptoDao = mock<CryptoDao>()
        whenever(cryptoDao.getCoins()).thenReturn(emptyList())

        val networkDataSource = mock<CoinNetworkDataSource>()
        whenever(networkDataSource.getCoins()).thenReturn(emptyList())

        val repository = DefaultCoinRepository(
            cryptoDao = cryptoDao,
            networkDataSource = networkDataSource
        )

        repository.getCoins().collect {}

        verify(networkDataSource).getCoins()
    }
}