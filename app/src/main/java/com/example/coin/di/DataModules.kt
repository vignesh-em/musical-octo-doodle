package com.example.coin.di

import android.content.Context
import androidx.room.Room
import com.example.coin.data.CryptoRepository
import com.example.coin.data.DefaultCryptoRepository
import com.example.coin.data.source.database.CryptoDatabase
import com.example.coin.data.source.network.CryptoNetworkDataSource
import com.example.coin.data.source.network.CryptoApi
import com.example.coin.data.source.network.RetrofitCryptoNetworkDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideCryptoNetworkDataSource(dataSource: RetrofitCryptoNetworkDataSource): CryptoNetworkDataSource =
        dataSource
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindCryptoRepository(repository: DefaultCryptoRepository): CryptoRepository
}

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): CryptoDatabase {
        return Room
            .databaseBuilder(context, CryptoDatabase::class.java, "crypto-database.db")
            .build()
    }

    @Singleton
    @Provides
    fun provideCryptoDao(database: CryptoDatabase) = database.cryptoDao()
}