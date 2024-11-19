package com.example.coin.ui.list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coin.R
import com.example.coin.ui.theme.CoinTheme

@Composable
fun NewCoinTag(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.tertiary, RoundedCornerShape(4.dp))
            .padding(horizontal = 4.dp, vertical = 0.dp)
    ) {
        Text(
            text = stringResource(R.string.tag_new).uppercase(),
            style = MaterialTheme.typography.bodySmall.copy(
                color = MaterialTheme.colorScheme.inverseOnSurface,
                fontSize = 8.sp,
                fontWeight = FontWeight.Bold
            ),
        )
    }
}

@Preview
@Composable
private fun NewCoinTagPreview() {
    CoinTheme {
        NewCoinTag()
    }
}