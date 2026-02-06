package com.youxiang8727.streamletmultiplatform.ui.transaction

import com.youxiang8727.streamletmultiplatform.core.datetime.format
import com.youxiang8727.streamletmultiplatform.core.mvi.UiState
import com.youxiang8727.streamletmultiplatform.domain.transaction.model.Category
import com.youxiang8727.streamletmultiplatform.domain.transaction.model.TransactionData
import com.youxiang8727.streamletmultiplatform.domain.transaction.model.TransactionType
import com.youxiang8727.streamletmultiplatform.domain.transaction.model.defaultTransactionType
import com.youxiang8727.streamletmultiplatform.domain.transaction.model.getDefaultCategory
import com.youxiang8727.streamletmultiplatform.domain.transaction.model.toCategory
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock

data class TransactionScreenUiState(
    val id: Long = 0,
    val date: LocalDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
    val transactionType: TransactionType = defaultTransactionType,
    val category: Category = getDefaultCategory(transactionType).toCategory(),
    val categories: List<Category> = emptyList(),
    val title: String = "",
    val amountString: String = "",
    val description: String = ""
): UiState {
    companion object {
        const val MAX_TITLE_LENGTH = 10
        const val MAX_AMOUNT = Int.MAX_VALUE
        const val MAX_DESCRIPTION_LENGTH = 100
    }

    val dateString = format(
        localDate = date,
        pattern = "yyyy-MM-dd"
    )

    val amount = amountString.toDoubleOrNull() ?: 0.0

    val saveable = amount != 0.0
}

fun TransactionScreenUiState.toTransactionData(): TransactionData = TransactionData(
    id = id,
    title = title,
    date = date,
    amount = amount,
    description = description,
    category = category
)
