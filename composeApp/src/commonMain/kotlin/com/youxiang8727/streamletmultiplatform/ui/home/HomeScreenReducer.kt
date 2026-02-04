package com.youxiang8727.streamletmultiplatform.ui.home

import com.youxiang8727.streamletmultiplatform.core.mvi.Reducer

class HomeScreenReducer: Reducer<HomeScreenUiState, HomeScreenUiEvent>() {
    override fun reduce(state: HomeScreenUiState, event: HomeScreenUiEvent): HomeScreenUiState {
        return when (event) {
            is HomeScreenUiEvent.OnDateSelected -> {
                state.copy(
                    selectedDate = event.date
                )
            }
            is HomeScreenUiEvent.OnTransactionDataUpdated -> {
                state.copy(
                    income = event.income,
                    incomeTransactions = event.incomeTransactions,
                    expense = event.expense,
                    expenseTransactions = event.expenseTransactions
                )
            }
        }
    }
}