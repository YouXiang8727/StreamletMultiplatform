package com.youxiang8727.streamletmultiplatform.domain.settings.model

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.StringResource
import streamletmultiplatform.composeapp.generated.resources.Res
import streamletmultiplatform.composeapp.generated.resources.app_theme_mode_dark
import streamletmultiplatform.composeapp.generated.resources.app_theme_mode_light
import streamletmultiplatform.composeapp.generated.resources.app_theme_mode_system

enum class AppThemeMode(
    val stringResource: StringResource
) {
    SYSTEM(stringResource = Res.string.app_theme_mode_system) {
        @Composable
        override fun isSystemInDarkTheme(): Boolean {
            return com.youxiang8727.streamletmultiplatform.core.ui.isSystemInDarkTheme()
        }
    },
    LIGHT(stringResource = Res.string.app_theme_mode_light) {
        @Composable
        override fun isSystemInDarkTheme(): Boolean {
            return false
        }
    },
    DARK(stringResource = Res.string.app_theme_mode_dark) {
        @Composable
        override fun isSystemInDarkTheme(): Boolean {
            return true
        }
    };

    @Composable
    abstract fun isSystemInDarkTheme(): Boolean
}