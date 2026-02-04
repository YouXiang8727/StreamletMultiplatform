package com.youxiang8727.streamletmultiplatform.ui.home

import com.youxiang8727.streamletmultiplatform.core.mvi.UiEvent
import kotlinx.datetime.LocalDate

sealed interface HomeScreenUiEvent: UiEvent {
    data class OnDateSelected(val date: LocalDate): HomeScreenUiEvent

    data class OnTransactionDataUpdated(
        val income: Double,
        val incomeTransactions: List<TransactionItemUiState>,
        val expense: Double,
        val expenseTransactions: List<TransactionItemUiState>
    ): HomeScreenUiEvent
}