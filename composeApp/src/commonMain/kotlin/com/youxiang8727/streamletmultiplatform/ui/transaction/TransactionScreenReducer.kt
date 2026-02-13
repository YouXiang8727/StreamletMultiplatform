package com.youxiang8727.streamletmultiplatform.ui.transaction

import com.youxiang8727.streamletmultiplatform.core.mvi.Reducer
import com.youxiang8727.streamletmultiplatform.domain.transaction.model.toTransactionScreenUiState

class TransactionScreenReducer: Reducer<TransactionScreenUiState, TransactionScreenUiEvent>() {
    override fun reduce(
        state: TransactionScreenUiState,
        event: TransactionScreenUiEvent
    ): TransactionScreenUiState {
        return when (event) {
            is TransactionScreenUiEvent.OnTransactionDataUpdated -> {
                event.transactionData.toTransactionScreenUiState()
            }
            is TransactionScreenUiEvent.OnTransactionTypeChanged -> {
                state.copy(
                    transactionType = event.transactionType,
                    categories = event.categories,
                    category = event.selectedCategory
                )
            }
            is TransactionScreenUiEvent.OnDateSelected -> {
                state.copy(
                    date = event.date
                )
            }
            is TransactionScreenUiEvent.OnTitleChanged -> {
                state.copy(
                    title = event.title
                )
            }
            is TransactionScreenUiEvent.OnCategoryChanged -> {
                state.copy(
                    category = event.category
                )
            }
            is TransactionScreenUiEvent.OnAmountChanged -> {
                state.copy(
                    amountString = event.amountString
                )
            }
            is TransactionScreenUiEvent.OnDescriptionChanged -> {
                state.copy(
                    description = event.description
                )
            }
            TransactionScreenUiEvent.OnSaveTransaction -> {
                state.copy(
                    id = 0L,
                    title = "",
                    amountString = "",
                    description = ""
                )
            }

            is TransactionScreenUiEvent.OnNewTransactionData -> {
                state.copy(
                    date = event.date,
                    transactionType = event.transactionType,
                    categories = event.categories,
                    category = event.category
                )
            }
        }
    }
}