package com.example.coin.data

import com.example.coin.data.source.database.CryptoDao
import com.example.coin.data.source.network.CryptoNetworkDataSource
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class DefaultCryptoRepositoryTest {
    @Test
    fun `when database is empty, network call is made`() = runTest {
        val cryptoDao = mock<CryptoDao>()
        whenever(cryptoDao.getCryptos()).thenReturn(emptyList())

        val networkDataSource = mock<CryptoNetworkDataSource>()
        whenever(networkDataSource.getCrypto()).thenReturn(emptyList())

        val repository = DefaultCryptoRepository(
            cryptoDao = cryptoDao,
            networkDataSource = networkDataSource
        )

        repository.getCryptos().collect {}

        verify(networkDataSource).getCrypto()
    }
}