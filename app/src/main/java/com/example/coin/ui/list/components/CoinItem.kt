package com.example.coin.ui.list.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coin.data.models.Crypto
import com.example.coin.data.models.Type

@Composable
fun CryptoItem(crypto: Crypto, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier
                .weight(1.0f)
                .padding(8.dp)
        ) {
            Text(text = crypto.name)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = crypto.symbol)
        }

        Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.padding(8.dp))
    }
}

@Preview
@Composable
private fun CryptoItemPreview() {
    CryptoItem(
        crypto = Crypto(
            name = "Bitcoin",
            symbol = "BTC",
            isNew = false,
            isActive = true,
            type = Type.Coin
        ),
        modifier = Modifier.fillMaxWidth()
    )
}