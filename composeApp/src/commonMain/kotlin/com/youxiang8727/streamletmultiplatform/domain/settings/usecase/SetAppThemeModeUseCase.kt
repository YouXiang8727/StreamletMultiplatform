package com.youxiang8727.streamletmultiplatform.domain.settings.usecase

import com.youxiang8727.streamletmultiplatform.domain.settings.model.AppThemeMode
import com.youxiang8727.streamletmultiplatform.domain.settings.repository.SettingsRepository

class SetAppThemeModeUseCase(
    private val settingsRepository: SettingsRepository
) {
    suspend operator fun invoke(appThemeMode: AppThemeMode) {
        settingsRepository.setAppThemeMode(appThemeMode)
    }
}