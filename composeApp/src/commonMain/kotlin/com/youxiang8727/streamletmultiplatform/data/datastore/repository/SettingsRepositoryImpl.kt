package com.youxiang8727.streamletmultiplatform.data.datastore.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.youxiang8727.streamletmultiplatform.domain.settings.model.AppThemeMode
import com.youxiang8727.streamletmultiplatform.domain.settings.repository.SettingsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class SettingsRepositoryImpl(private val dataStore: DataStore<Preferences>): SettingsRepository {
    private val appThemeModeKey = stringPreferencesKey("app_theme_mode")

    override fun getAppThemeMode(): Flow<AppThemeMode> {
        return dataStore.data.map { preferences ->
            preferences[appThemeModeKey]
        }.map { appThemeMode ->
            appThemeMode?.let { AppThemeMode.valueOf(it) } ?: AppThemeMode.SYSTEM
        }
    }

    override suspend fun setAppThemeMode(appThemeMode: AppThemeMode) {
        withContext(Dispatchers.IO) {
            dataStore.updateData {
                it.toMutablePreferences().also { preferences ->
                    preferences[appThemeModeKey] = appThemeMode.name
                }
            }
        }
    }
}