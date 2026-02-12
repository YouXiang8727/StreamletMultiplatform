package com.youxiang8727.streamletmultiplatform.domain.settings.repository

import com.youxiang8727.streamletmultiplatform.domain.settings.model.AppThemeMode
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    fun getAppThemeMode(): Flow<AppThemeMode>

    suspend fun setAppThemeMode(appThemeMode: AppThemeMode)
}