package com.example.coin.ui.list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coin.ui.list.Filter

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CoinChipGroup(
    filters: List<Filter>,
    onFilterAdd: (Filter) -> Unit,
    onFilterRemove: (Filter) -> Unit,
    modifier: Modifier = Modifier
) {
    FlowRow(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.inverseOnSurface)
            .padding(8.dp)
            .navigationBarsPadding()
    ) {
        filters.forEach { filter ->
            CoinFilterChip(
                filter = filter,
                onFilterAdd = onFilterAdd,
                onFilterRemove = onFilterRemove,
                modifier = Modifier.padding(end = 8.dp)
            )
        }
    }
}

@Preview
@Composable
private fun CoinChipGroupPreview() {
    CoinChipGroup(filters = Filter.entries, onFilterAdd = {}, onFilterRemove = {})
}

@Composable
private fun CoinFilterChip(
    filter: Filter,
    onFilterAdd: (Filter) -> Unit,
    onFilterRemove: (Filter) -> Unit,
    modifier: Modifier = Modifier
) {
    var selected by rememberSaveable { mutableStateOf(false) }
    FilterChip(
        selected,
        leadingIcon = { if (selected) Icon(Icons.Filled.Done, null) },
        label = {
            Text(
                stringResource(filter.filterName),
                style = MaterialTheme.typography.bodySmall
            )
        },
        onClick = {
            selected = !selected
            if (selected) onFilterAdd(filter)
            else onFilterRemove(filter)
        },
        modifier = modifier
    )
}