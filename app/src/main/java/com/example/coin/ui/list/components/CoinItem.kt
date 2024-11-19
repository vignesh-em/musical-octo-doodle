package com.example.coin.ui.list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coin.R
import com.example.coin.data.models.Crypto
import com.example.coin.data.models.Type
import com.example.coin.data.models.isCoin

@Composable
fun CryptoItem(crypto: Crypto, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .weight(1.0f)
                .padding(8.dp)
                .heightIn(56.dp)
        ) {
            Text(text = crypto.symbol, style = MaterialTheme.typography.titleMedium)
            Text(text = crypto.name, style = MaterialTheme.typography.bodySmall)
        }

        Box(contentAlignment = Alignment.Center) {
            Image(painter = painterResource(getCryptoIcon(crypto)), contentDescription = null)
        }
    }
}

private fun getCryptoIcon(crypto: Crypto) = when {
    crypto.isActive && crypto.isCoin() -> R.mipmap.ic_active_coin
    crypto.isActive && !crypto.isCoin() -> R.mipmap.ic_active_token
    else -> R.mipmap.ic_inactive
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