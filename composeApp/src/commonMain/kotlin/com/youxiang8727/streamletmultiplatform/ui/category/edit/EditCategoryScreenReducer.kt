package com.youxiang8727.streamletmultiplatform.ui.category.edit

import com.youxiang8727.streamletmultiplatform.core.mvi.Reducer

class EditCategoryScreenReducer: Reducer<EditCategoryScreenUiState, EditCategoryScreenUiEvent>() {
    override fun reduce(
        state: EditCategoryScreenUiState,
        event: EditCategoryScreenUiEvent
    ): EditCategoryScreenUiState {
        return when (event) {
            is EditCategoryScreenUiEvent.OnDataInitialized -> {
                EditCategoryScreenUiState(
                    id = event.id,
                    originalName = event.name,
                    name = event.name,
                    transactionType = event.transactionType,
                    showDuplicatedAlertDialog = false
                )
            }

            EditCategoryScreenUiEvent.OnDismissCategoryDuplicatedAlertDialog -> {
                state.copy(
                    showDuplicatedAlertDialog = false
                )
            }

            is EditCategoryScreenUiEvent.OnNameChanged -> {
                state.copy(
                    name = event.name
                )
            }

            EditCategoryScreenUiEvent.OnShowCategoryDuplicatedAlertDialog -> {
                state.copy(
                    showDuplicatedAlertDialog = true
                )
            }

            is EditCategoryScreenUiEvent.OnTransactionTypeChanged -> {
                state.copy(
                    transactionType = event.transactionType
                )
            }
        }
    }
}