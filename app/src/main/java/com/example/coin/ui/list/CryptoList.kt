package com.example.coin.ui.list

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.coin.data.models.Crypto
import com.example.coin.ui.list.components.CryptoItem

@Composable
fun CryptoList(coins: List<Crypto>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(
            items = coins,
            key = { crypto -> crypto.symbol }
        ) { crypto ->
            CryptoItem(crypto, modifier = Modifier.padding(horizontal = 8.dp))
        }
    }
}