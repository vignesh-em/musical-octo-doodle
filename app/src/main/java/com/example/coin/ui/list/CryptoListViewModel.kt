package com.example.coin.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coin.data.models.Crypto
import com.example.coin.domain.FilterCryptosUseCase
import com.example.coin.domain.GetCoinsUseCase
import com.example.coin.domain.SearchCryptosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class CryptoListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase,
    private val filterCryptosUseCase: FilterCryptosUseCase,
    private val searchCryptosUseCase: SearchCryptosUseCase,
) : ViewModel() {
    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    private val allCoins = MutableStateFlow<List<Crypto>>(emptyList())

    private val activeFilters = MutableStateFlow<List<Filter>>(emptyList())

    private val _cryptoList = MutableStateFlow<List<Crypto>>(emptyList())
    val cryptoList = allCoins
        .combine(_query) { coins, query -> searchCryptosUseCase(coins, query) }
        .combine(activeFilters) { coins, filters ->
            filterCryptosUseCase(
                coins,
                filters.map { filter -> filter.predicate })
        }
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    fun updateQuery(newQuery: String) {
        _query.value = newQuery
    }

    fun onFilterAdd(filter: Filter) {
        activeFilters.value.toMutableList().let { newFilters ->
            newFilters.add(filter)
            activeFilters.value = newFilters
        }
        updateFilteredCoins()
    }

    private fun updateFilteredCoins() {
        _cryptoList.value = filterCryptosUseCase(
            _cryptoList.value,
            activeFilters.value.map { activeFilter -> activeFilter.predicate })
    }

    fun onFilterRemove(filter: Filter) {
        activeFilters.value.toMutableList().let { newFilters ->
            newFilters.remove(filter)
            activeFilters.value = newFilters
        }
        updateFilteredCoins()
    }

    init {
        viewModelScope.launch {
            getCoinsUseCase()
                .collect { coins -> allCoins.value = coins }
        }
    }
}