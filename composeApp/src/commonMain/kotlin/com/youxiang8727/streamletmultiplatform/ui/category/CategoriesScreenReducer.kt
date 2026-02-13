package com.youxiang8727.streamletmultiplatform.ui.category

import com.youxiang8727.streamletmultiplatform.core.mvi.Reducer

class CategoriesScreenReducer: Reducer<CategoriesScreenUiState, CategoriesScreenUiEvent>() {
    override fun reduce(
        state: CategoriesScreenUiState,
        event: CategoriesScreenUiEvent
    ): CategoriesScreenUiState {
        return when(event) {
            is CategoriesScreenUiEvent.OnCategoriesChanged -> {
                state.copy(
                    categories = event.categories
                )
            }
            is CategoriesScreenUiEvent.OnTransactionTypeChanged -> {
                state.copy(
                    transactionType = event.transactionType
                )
            }
        }
    }
}