package com.example.coin.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coin.data.models.Crypto
import com.example.coin.domain.FilterCryptosUseCase
import com.example.coin.domain.GetCoinsUseCase
import com.example.coin.domain.SearchCryptosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class CryptoListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase,
    private val filterCryptosUseCase: FilterCryptosUseCase,
    private val searchCryptosUseCase: SearchCryptosUseCase,
) : ViewModel() {
    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    private val _allCoins = MutableStateFlow<List<Crypto>>(emptyList())

    private val _activeFilters = MutableStateFlow<List<Filter>>(emptyList())

    val uiState = _allCoins
        .combine(query) { coins, query ->
            searchCryptosUseCase(coins, query)
        }
        .combine(_activeFilters) { coins, filters ->
            filterCryptosUseCase(coins, filters.map { filter -> filter.predicate })
        }
        .mapLatest { coins ->
            if (coins.isEmpty()) {
                delay(500)
                UiState.Empty
            } else UiState.Success(coins)
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, UiState.Loading)

    fun updateQuery(newQuery: String) {
        _query.value = newQuery
    }

    fun onFilterAdd(filter: Filter) {
        _activeFilters.value
            .toMutableList()
            .apply { add(filter) }
            .let { newFilters -> _activeFilters.value = newFilters }
    }

    fun onFilterRemove(filter: Filter) {
        _activeFilters.value
            .toMutableList()
            .apply { remove(filter) }
            .let { newFilters -> _activeFilters.value = newFilters }
    }

    init {
        viewModelScope.launch {
            getCoinsUseCase()
                .collect { coins -> _allCoins.value = coins }
        }
    }
}