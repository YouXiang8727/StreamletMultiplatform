package com.youxiang8727.streamletmultiplatform.ui.settings

import com.youxiang8727.streamletmultiplatform.core.mvi.UiState
import com.youxiang8727.streamletmultiplatform.domain.settings.model.AppThemeMode

data class SettingsScreenUiState(
    val appThemeMode: AppThemeMode = AppThemeMode.SYSTEM
): UiState
