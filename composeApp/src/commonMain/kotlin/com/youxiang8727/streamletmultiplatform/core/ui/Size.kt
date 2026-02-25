package com.youxiang8727.streamletmultiplatform.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

@Composable
fun Dp.toPx(): Float {
    val density = LocalDensity.current
    return with(density) {
        this@toPx.toPx()
    }
}

@Composable
fun Float.toDp(): Dp {
    val density = LocalDensity.current
    return with(density) {
        this@toDp.toDp()
    }
}