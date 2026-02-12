package com.youxiang8727.streamletmultiplatform

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.youxiang8727.streamletmultiplatform.domain.settings.model.AppThemeMode
import com.youxiang8727.streamletmultiplatform.domain.settings.usecase.GetAppThemeModeUseCase
import com.youxiang8727.streamletmultiplatform.domain.transaction.usecase.InsertDefaultCategoriesUseCase
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AppViewModel(
    private val insertDefaultCategoriesUseCase: InsertDefaultCategoriesUseCase,
    private val getAppThemeModeUseCase: GetAppThemeModeUseCase
): ViewModel() {
    val appThemeMode: StateFlow<AppThemeMode> = getAppThemeModeUseCase()
        .stateIn(
            scope = viewModelScope,
            started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5000),
            initialValue = AppThemeMode.SYSTEM
        )

    init {
        viewModelScope.launch {
            insertDefaultCategoriesUseCase()
        }
    }
}