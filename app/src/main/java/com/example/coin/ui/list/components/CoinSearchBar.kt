package com.example.coin.ui.list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coin.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoinSearchBar(query: String, onQueryChange: (String) -> Unit, modifier: Modifier = Modifier) {
    SearchBar(
        query = query,
        onQueryChange = onQueryChange,
        onSearch = { },
        active = false,
        onActiveChange = { },
        placeholder = {
            Text(stringResource(R.string.search_bar_placeholder))
        },
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = null,
            )
        },
        trailingIcon = {
            if (query.isNotBlank()) Icon(
                Icons.Default.Clear,
                contentDescription = stringResource(R.string.content_desc_clear_query),
                modifier = Modifier.clickable { onQueryChange("") })
        },
        modifier = modifier.padding(8.dp)
    ) {

    }
}

@Preview
@Composable
private fun CoinSearchBarPreview() {
    CoinSearchBar(query = "", onQueryChange = {})
}