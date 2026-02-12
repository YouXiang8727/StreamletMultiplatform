package com.youxiang8727.streamletmultiplatform.domain.settings.usecase

import com.youxiang8727.streamletmultiplatform.domain.settings.model.AppThemeMode
import com.youxiang8727.streamletmultiplatform.domain.settings.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow

class GetAppThemeModeUseCase(
    private val settingsRepository: SettingsRepository
) {
    operator fun invoke(): Flow<AppThemeMode> = settingsRepository.getAppThemeMode()
}