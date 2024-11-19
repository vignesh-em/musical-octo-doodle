package com.example.coin.ui.list

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.coin.ui.list.components.CoinChipGroup
import com.example.coin.ui.list.components.CoinSearchBar
import com.example.coin.ui.list.components.CryptoItem

@Composable
fun CryptoListScreen(
    modifier: Modifier = Modifier,
    viewModel: CryptoListViewModel = hiltViewModel(),
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            val query by viewModel.query.collectAsState()
            CoinSearchBar(query = query, onQueryChange = viewModel::updateQuery)
        },
        bottomBar = {
            CoinChipGroup(
                filters = Filter.entries,
                onFilterAdd = viewModel::onFilterAdd,
                onFilterRemove = viewModel::onFilterRemove
            )
        }
    ) { paddingValues ->

        val coins by viewModel.cryptoList.collectAsState()

        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(
                items = coins,
                key = { crypto -> crypto.symbol }
            ) { crypto ->
                CryptoItem(crypto, modifier = Modifier.padding(horizontal = 8.dp))
            }
        }
    }
}