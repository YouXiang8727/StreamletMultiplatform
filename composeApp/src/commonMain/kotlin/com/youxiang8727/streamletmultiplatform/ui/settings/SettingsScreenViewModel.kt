package com.youxiang8727.streamletmultiplatform.ui.settings

import androidx.lifecycle.viewModelScope
import com.youxiang8727.streamletmultiplatform.core.mvi.MviViewModel
import com.youxiang8727.streamletmultiplatform.domain.settings.model.AppThemeMode
import com.youxiang8727.streamletmultiplatform.domain.settings.usecase.GetAppThemeModeUseCase
import com.youxiang8727.streamletmultiplatform.domain.settings.usecase.SetAppThemeModeUseCase
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsScreenViewModel(
    getAppThemeModeUseCase: GetAppThemeModeUseCase,
    private val setAppThemeModeUseCase: SetAppThemeModeUseCase
): MviViewModel<SettingsScreenUiState, SettingsScreenUiEvent, Nothing>(
    initialState = SettingsScreenUiState(),
    reducer = SettingsScreenReducer()
) {
    private val _appThemeMode: StateFlow<AppThemeMode> = getAppThemeModeUseCase()
        .stateIn(
            scope = viewModelScope,
            started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5000),
            initialValue = AppThemeMode.SYSTEM
        )

    fun saveAppThemeMode(appThemeMode: AppThemeMode) {
        if (appThemeMode == _appThemeMode.value) return

        viewModelScope.launch {
            setAppThemeModeUseCase(appThemeMode)
        }
    }

    init {
        viewModelScope.launch {
            _appThemeMode.collect { appThemeMode ->
                dispatch(
                    SettingsScreenUiEvent.OnAppThemeModeChange(appThemeMode)
                )
            }
        }
    }
}