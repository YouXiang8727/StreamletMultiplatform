package com.youxiang8727.streamletmultiplatform

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.youxiang8727.streamletmultiplatform.domain.transaction.usecase.InsertDefaultCategoriesUseCase
import kotlinx.coroutines.launch

class AppViewModel(
    private val insertDefaultCategoriesUseCase: InsertDefaultCategoriesUseCase
): ViewModel() {
    init {
        viewModelScope.launch {
            insertDefaultCategoriesUseCase()
        }
    }
}