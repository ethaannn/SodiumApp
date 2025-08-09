package com.ethan.sodium.extension

import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp

fun Dp.dp2px( density: Density): Float=  with(receiver = density) { this@dp2px.toPx() }

fun Int.px2dp(density: Density): Dp = with(receiver = density) { this@px2dp.toDp() }