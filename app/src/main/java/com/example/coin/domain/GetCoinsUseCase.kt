package com.example.coin.domain

import com.example.coin.data.CoinRepository
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(private val coinRepository: CoinRepository) {
    suspend operator fun invoke() = coinRepository.getCoins()
}