package com.example.coin.ui.list

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
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

@Composable
private fun NoListState(
    @DrawableRes icon: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier,
    rotation: Float = 0f,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = null,
            modifier = Modifier
                .size(72.dp)
                .rotate(rotation)
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            stringResource(text),
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Composable
fun ErrorState(modifier: Modifier = Modifier) {
    NoListState(R.mipmap.ic_inactive, R.string.something_went_wrong, modifier)
}

@Composable
fun EmptyState(modifier: Modifier = Modifier) {
    NoListState(R.mipmap.ic_active_coin, R.string.no_coins_found, modifier)
}

@Composable
fun ListLoadingState(modifier: Modifier = Modifier) {
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

    NoListState(R.mipmap.ic_active_token, R.string.loading, modifier, rotation)
}

@Preview
@Composable
private fun EmptyStatePreview() {
    EmptyState()
}

@Preview
@Composable
private fun ErrorStatePreview() {
    ErrorState()
}

@Preview
@Composable
private fun LoadingStatePreview() {
    ListLoadingState()
}