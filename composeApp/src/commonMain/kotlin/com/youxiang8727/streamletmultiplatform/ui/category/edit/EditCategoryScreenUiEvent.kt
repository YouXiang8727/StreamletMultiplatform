package com.youxiang8727.streamletmultiplatform.ui.category.edit

import com.youxiang8727.streamletmultiplatform.core.mvi.UiEvent
import com.youxiang8727.streamletmultiplatform.domain.transaction.model.TransactionType

sealed interface EditCategoryScreenUiEvent: UiEvent {
    data class OnDataInitialized(
        val id: Long,
        val name: String,
        val transactionType: TransactionType
    ): EditCategoryScreenUiEvent

    data class OnTransactionTypeChanged(val transactionType: TransactionType): EditCategoryScreenUiEvent

    data class OnNameChanged(val name: String): EditCategoryScreenUiEvent

    data object OnShowCategoryDuplicatedAlertDialog: EditCategoryScreenUiEvent

    data object OnDismissCategoryDuplicatedAlertDialog: EditCategoryScreenUiEvent
}