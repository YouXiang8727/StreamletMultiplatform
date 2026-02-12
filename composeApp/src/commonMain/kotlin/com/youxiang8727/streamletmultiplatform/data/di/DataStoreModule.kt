package com.youxiang8727.streamletmultiplatform.data.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.youxiang8727.streamletmultiplatform.data.datastore.createDataStoreByPlatform
import com.youxiang8727.streamletmultiplatform.data.datastore.repository.SettingsRepositoryImpl
import com.youxiang8727.streamletmultiplatform.domain.settings.repository.SettingsRepository
import com.youxiang8727.streamletmultiplatform.domain.settings.usecase.GetAppThemeModeUseCase
import com.youxiang8727.streamletmultiplatform.domain.settings.usecase.SetAppThemeModeUseCase
import org.koin.dsl.module

val dataStoreModule = module {
    single<DataStore<Preferences>> { createDataStoreByPlatform() }

    single<SettingsRepository> { SettingsRepositoryImpl(get()) }

    factory<GetAppThemeModeUseCase> { GetAppThemeModeUseCase(get()) }

    factory<SetAppThemeModeUseCase> { SetAppThemeModeUseCase(get()) }
}