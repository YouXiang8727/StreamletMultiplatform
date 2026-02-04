package com.youxiang8727.streamletmultiplatform.ui.home

import com.youxiang8727.streamletmultiplatform.core.datetime.format
import com.youxiang8727.streamletmultiplatform.core.mvi.UiState
import com.youxiang8727.streamletmultiplatform.core.ui.UiText
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock

data class HomeScreenUiState(
    val selectedDate: LocalDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
    val income: Double = 0.0,
    val incomeTransactions: List<TransactionItemUiState> = emptyList(),
    val expense: Double = 0.0,
    val expenseTransactions: List<TransactionItemUiState> = emptyList()
): UiState {
    val selectedDateString = format(
        localDate = selectedDate,
        pattern = "yyyy-MM-dd"
    )
}

data class TransactionItemUiState(
    val id: Long = 0,
    val title: String = "",
    val amount: Double = 0.0,
    val categoryUiText: UiText = UiText.DynamicString("")
)
