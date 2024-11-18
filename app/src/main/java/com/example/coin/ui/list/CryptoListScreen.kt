package com.example.coin.ui.list

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.coin.ui.list.components.CoinSearchBar

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
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {

        }
    }
}