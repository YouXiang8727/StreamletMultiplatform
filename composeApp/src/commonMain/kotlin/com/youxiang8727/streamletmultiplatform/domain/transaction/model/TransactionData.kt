package com.youxiang8727.streamletmultiplatform.domain.transaction.model

import com.youxiang8727.streamletmultiplatform.ui.home.TransactionItemUiState
import kotlinx.datetime.LocalDate

data class TransactionData(
    val id: Long,
    val title: String,
    val date: LocalDate,
    val amount: Double,
    val description: String,
    val category: Category
)

fun TransactionData.toTransactionItemUiState(): TransactionItemUiState = TransactionItemUiState(
    id = id,
    title = title,
    amount = amount,
    categoryUiText = category.categoryUiText
)
