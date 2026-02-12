package com.youxiang8727.streamletmultiplatform.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.materialkolor.rememberDynamicColorScheme
import com.youxiang8727.streamletmultiplatform.core.ui.isSystemInDarkTheme

private val primaryLight = Color(0xFF0D631B)

@Composable
fun AppTheme(
    seedColor: Color = primaryLight,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colorScheme = rememberDynamicColorScheme(seedColor = seedColor, isDark = darkTheme)

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}

