package com.youxiang8727.streamletmultiplatform.ui.category

import com.youxiang8727.streamletmultiplatform.core.mvi.UiState
import com.youxiang8727.streamletmultiplatform.domain.transaction.model.Category
import com.youxiang8727.streamletmultiplatform.domain.transaction.model.TransactionType

data class CategoriesScreenUiState(
    val transactionType: TransactionType = TransactionType.INCOME,
    val categories: List<Category> = emptyList()
): UiState
