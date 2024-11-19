package com.example.coin.ui.list

import com.example.coin.data.models.Crypto

sealed class UiState {
    data object Loading : UiState()
    data object Empty : UiState()
    data object Error : UiState()
    data class Success(val coins: List<Crypto>) : UiState()
}