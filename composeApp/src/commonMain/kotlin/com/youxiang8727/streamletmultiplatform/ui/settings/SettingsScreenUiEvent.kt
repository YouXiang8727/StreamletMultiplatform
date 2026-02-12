package com.youxiang8727.streamletmultiplatform.ui.settings

import com.youxiang8727.streamletmultiplatform.core.mvi.UiEvent
import com.youxiang8727.streamletmultiplatform.domain.settings.model.AppThemeMode

sealed interface SettingsScreenUiEvent: UiEvent {
    data class OnAppThemeModeChange(val appThemeMode: AppThemeMode) : SettingsScreenUiEvent
}