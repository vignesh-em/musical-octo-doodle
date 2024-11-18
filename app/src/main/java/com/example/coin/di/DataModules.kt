package com.example.coin.di

import com.example.coin.data.CoinRepository
import com.example.coin.data.DefaultCoinRepository
import com.example.coin.data.source.network.CoinNetworkDataSource
import com.example.coin.data.source.network.CryptoApi
import com.example.coin.data.source.network.RetrofitCoinNetworkDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideNetworkJson(): Json = Json { ignoreUnknownKeys = true }

    @Singleton
    @Provides
    fun provideCryptoApi(networkJson: Json): CryptoApi {
        return Retrofit.Builder()
            .baseUrl("https://37656be98b8f42ae8348e4da3ee3193f.api.mockbin.io/")
            .addConverterFactory(
                networkJson.asConverterFactory(
                    MediaType.get("application/json; charset=UTF8")
                )
            )
            .build()
            .create<CryptoApi>()
    }

    @Singleton
    @Provides
    fun provideCoinNetworkDataSource(dataSource: RetrofitCoinNetworkDataSource): CoinNetworkDataSource =
        dataSource
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindCoinRepository(repository: DefaultCoinRepository): CoinRepository
}