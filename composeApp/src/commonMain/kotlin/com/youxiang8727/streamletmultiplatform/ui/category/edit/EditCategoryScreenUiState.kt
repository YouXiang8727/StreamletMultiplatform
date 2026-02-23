package com.youxiang8727.streamletmultiplatform.ui.category.edit

import com.youxiang8727.streamletmultiplatform.core.mvi.UiState
import com.youxiang8727.streamletmultiplatform.domain.transaction.model.TransactionType

data class EditCategoryScreenUiState(
    val id: Long = 0L,
    val originalName: String = "",
    val name: String = "",
    val transactionType: TransactionType = TransactionType.INCOME,
    val showDuplicatedAlertDialog: Boolean = false
): UiState {
    val saveable: Boolean = name.isNotBlank() && name != originalName
}
