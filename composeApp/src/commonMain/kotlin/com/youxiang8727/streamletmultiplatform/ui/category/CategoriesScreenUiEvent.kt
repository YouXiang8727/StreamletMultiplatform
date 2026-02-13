package com.youxiang8727.streamletmultiplatform.ui.category

import com.youxiang8727.streamletmultiplatform.core.mvi.UiEvent
import com.youxiang8727.streamletmultiplatform.domain.transaction.model.Category
import com.youxiang8727.streamletmultiplatform.domain.transaction.model.TransactionType

sealed interface CategoriesScreenUiEvent: UiEvent {
    data class OnTransactionTypeChanged(
        val transactionType: TransactionType
    ): CategoriesScreenUiEvent

    data class OnCategoriesChanged(
        val categories: List<Category>
    ): CategoriesScreenUiEvent
}