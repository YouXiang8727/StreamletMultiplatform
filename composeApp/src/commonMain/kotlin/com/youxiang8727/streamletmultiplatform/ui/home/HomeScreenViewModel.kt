package com.youxiang8727.streamletmultiplatform.ui.home

import androidx.lifecycle.viewModelScope
import com.youxiang8727.streamletmultiplatform.core.mvi.MviViewModel
import com.youxiang8727.streamletmultiplatform.domain.transaction.model.TransactionType
import com.youxiang8727.streamletmultiplatform.domain.transaction.model.toTransactionItemUiState
import com.youxiang8727.streamletmultiplatform.domain.transaction.usecase.GetTransactionByDateUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate

class HomeScreenViewModel(
    getTransactionByDateUseCase: GetTransactionByDateUseCase
): MviViewModel<HomeScreenUiState, HomeScreenUiEvent, Nothing>(
    initialState = HomeScreenUiState(),
    reducer = HomeScreenReducer()
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    private val transactions = state.flatMapLatest {
        getTransactionByDateUseCase(it.selectedDate)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun onDateSelected(
        date: LocalDate
    ) {
        dispatch(HomeScreenUiEvent.OnDateSelected(date))
    }


    init {
        viewModelScope.launch {
            transactions.collect { transactions ->
                val incomeTransactions = transactions.filter { transactionData ->
                    transactionData.category.type == TransactionType.INCOME
                }

                val incomeAmount = incomeTransactions.sumOf { transactionData ->
                    transactionData.amount
                }

                val expenseTransactions = transactions.filter { transactionData ->
                    transactionData.category.type == TransactionType.EXPENSE
                }

                val expenseAmount = expenseTransactions.sumOf { transactionData ->
                    transactionData.amount
                }

                dispatch(
                    HomeScreenUiEvent.OnTransactionDataUpdated(
                        income = incomeAmount,
                        incomeTransactions = incomeTransactions.map { transactionData -> transactionData.toTransactionItemUiState() },
                        expense = expenseAmount,
                        expenseTransactions = expenseTransactions.map { transactionData -> transactionData.toTransactionItemUiState() }
                    )
                )
            }
        }
    }

}