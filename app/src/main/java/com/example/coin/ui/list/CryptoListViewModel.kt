package com.example.coin.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coin.data.models.Crypto
import com.example.coin.domain.FilterCryptosUseCase
import com.example.coin.domain.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class CryptoListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase,
    private val filterCryptosUseCase: FilterCryptosUseCase
) : ViewModel() {
    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    private val _cryptoList = MutableStateFlow<List<Crypto>>(emptyList())
    val cryptoList = _cryptoList


    private val activeFilters = mutableListOf<Filter>()

    fun updateQuery(newQuery: String) {
        _query.value = newQuery
    }

    fun onFilterAdd(filter: Filter) {
        activeFilters.add(filter)
        updateFilteredCoins()
    }

    private fun updateFilteredCoins() {
        _cryptoList.value = filterCryptosUseCase(
            _cryptoList.value,
            activeFilters.map { activeFilter -> activeFilter.predicate })
    }

    fun onFilterRemove(filter: Filter) {
        activeFilters.remove(filter)
        updateFilteredCoins()
    }

    init {
        viewModelScope.launch {
            getCoinsUseCase()
                .collect { coins -> _cryptoList.value = coins }
        }
    }
}