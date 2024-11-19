package com.example.coin.ui.list

import androidx.annotation.StringRes
import com.example.coin.R
import com.example.coin.data.models.Crypto
import com.example.coin.data.models.Type

enum class Filter(@StringRes val filterName: Int, val predicate: (Crypto) -> Boolean) {
    ActiveCoins(
        filterName = R.string.filter_active_coins,
        predicate = { crypto -> crypto.isActive }),

    InactiveCoins(
        filterName = R.string.filter_inactive_coins,
        predicate = { crypto -> crypto.isActive.not() }),

    NewCoins(filterName = R.string.filter_new_coins, predicate = { crypto -> crypto.isNew }),

    OnlyCoins(
        filterName = R.string.filter_only_coins,
        predicate = { crypto -> crypto.type == Type.Coin }),

    OnlyTokens(
        filterName = R.string.filter_only_tokens,
        predicate = { crypto -> crypto.type == Type.Token }),
}