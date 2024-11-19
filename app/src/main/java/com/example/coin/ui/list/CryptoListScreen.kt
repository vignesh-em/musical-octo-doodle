package com.example.coin.ui.list

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.coin.ui.list.components.CoinChipGroup
import com.example.coin.ui.list.components.CoinSearchBar
import com.example.coin.ui.list.components.CryptoList
import com.example.coin.ui.list.components.EmptyState
import com.example.coin.ui.list.components.ListLoadingState

@Composable
fun CryptoListScreen(
    modifier: Modifier = Modifier,
    viewModel: CryptoListViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsState()

    Scaffold(
        modifier = modifier,
        topBar = {
            val query by viewModel.query.collectAsState()
            CoinSearchBar(query = query, onQueryChange = viewModel::updateQuery)
        },
        bottomBar = {
            if (state is UiState.Empty || state is UiState.Success) {
                CoinChipGroup(
                    filters = Filter.entries,
                    onFilterAdd = viewModel::onFilterAdd,
                    onFilterRemove = viewModel::onFilterRemove
                )
            }
        }
    ) { paddingValues ->

        when (state) {
            UiState.Empty -> EmptyState(modifier = Modifier.padding(paddingValues))
            UiState.Loading -> ListLoadingState(modifier = Modifier.padding(paddingValues))
            is UiState.Success -> CryptoList(
                (state as UiState.Success).coins, modifier = Modifier.padding(paddingValues)
            )
        }
    }
}