package com.example.coin.ui.list.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coin.R
import com.example.coin.ui.theme.CoinTheme

@Composable
fun ListLoadingState(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val infiniteTransition = rememberInfiniteTransition(label = "infinite")

        val rotation by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 360f,
            label = "rotation",
            animationSpec = infiniteRepeatable(
                animation = tween(5_000, easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            )
        )

        Image(
            painter = painterResource(R.mipmap.ic_active_token),
            contentDescription = null,
            modifier = Modifier
                .size(72.dp)
                .rotate(rotation)
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            stringResource(R.string.loading),
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Preview
@Composable
private fun ListLoadingStatePreview() {
    CoinTheme {
        ListLoadingState()
    }
}