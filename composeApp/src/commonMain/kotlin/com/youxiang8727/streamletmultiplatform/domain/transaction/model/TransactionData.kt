package com.youxiang8727.streamletmultiplatform.domain.transaction.model

import com.youxiang8727.streamletmultiplatform.data.db.model.TransactionEntity
import com.youxiang8727.streamletmultiplatform.ui.home.TransactionItemUiState
import com.youxiang8727.streamletmultiplatform.ui.transaction.TransactionScreenUiState
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

fun TransactionData.toTransactionScreenUiState(): TransactionScreenUiState = TransactionScreenUiState(
    id = id,
    date = date,
    transactionType = category.type,
    category = category,
    categories = emptyList(),
    title = title,
    amountString = amount.toString(),
    description = description
)

fun TransactionData.toTransactionEntity(): TransactionEntity = TransactionEntity(
    id = id,
    title = title,
    amount = amount,
    categoryId = category.id,
    date = date,
    description = description
)
