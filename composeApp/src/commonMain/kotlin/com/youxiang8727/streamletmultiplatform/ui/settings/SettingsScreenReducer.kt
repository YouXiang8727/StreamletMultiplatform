package com.youxiang8727.streamletmultiplatform.ui.settings

import com.youxiang8727.streamletmultiplatform.core.mvi.Reducer

class SettingsScreenReducer: Reducer<SettingsScreenUiState, SettingsScreenUiEvent>() {
    override fun reduce(
        state: SettingsScreenUiState,
        event: SettingsScreenUiEvent
    ): SettingsScreenUiState {
        return when(event) {
            is SettingsScreenUiEvent.OnAppThemeModeChange -> {
                state.copy(
                    appThemeMode = event.appThemeMode
                )
            }
        }
    }
}