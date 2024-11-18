package com.example.coin.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coin.data.models.Crypto
import com.example.coin.domain.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class CryptoListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase,
) : ViewModel() {
    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    private val _coins = MutableStateFlow<List<Crypto>>(emptyList())
    val coins: StateFlow<List<Crypto>> = _coins

    fun updateQuery(newQuery: String) {
        _query.value = newQuery
    }

    init {
        viewModelScope.launch {
            getCoinsUseCase()
                .collect { coins ->
                    _coins.value = coins
                }
        }
    }
}