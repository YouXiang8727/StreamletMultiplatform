package com.youxiang8727.streamletmultiplatform.ui.transaction

import com.youxiang8727.streamletmultiplatform.core.mvi.UiEvent
import com.youxiang8727.streamletmultiplatform.domain.transaction.model.Category
import com.youxiang8727.streamletmultiplatform.domain.transaction.model.TransactionData
import com.youxiang8727.streamletmultiplatform.domain.transaction.model.TransactionType
import kotlinx.datetime.LocalDate

sealed interface TransactionScreenUiEvent: UiEvent {
    data class OnTransactionDataUpdated(val transactionData: TransactionData): TransactionScreenUiEvent

    data class OnTransactionTypeChanged(
        val transactionType: TransactionType,
        val categories: List<Category>,
        val selectedCategory: Category
    ): TransactionScreenUiEvent

    data class OnDateSelected(val date: LocalDate) : TransactionScreenUiEvent

    data class OnCategoryChanged(val category: Category) : TransactionScreenUiEvent

    data class OnTitleChanged(val title: String): TransactionScreenUiEvent

    data class OnAmountChanged(val amountString: String): TransactionScreenUiEvent

    data class OnDescriptionChanged(val description: String): TransactionScreenUiEvent

    data object OnSaveTransaction: TransactionScreenUiEvent
}