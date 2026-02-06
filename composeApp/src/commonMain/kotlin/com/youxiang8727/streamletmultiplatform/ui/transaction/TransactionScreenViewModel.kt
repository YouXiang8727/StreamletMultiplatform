package com.youxiang8727.streamletmultiplatform.ui.transaction

import androidx.lifecycle.viewModelScope
import com.youxiang8727.streamletmultiplatform.core.mvi.MviViewModel
import com.youxiang8727.streamletmultiplatform.domain.transaction.model.Category
import com.youxiang8727.streamletmultiplatform.domain.transaction.model.TransactionType
import com.youxiang8727.streamletmultiplatform.domain.transaction.model.getDefaultCategory
import com.youxiang8727.streamletmultiplatform.domain.transaction.model.toCategory
import com.youxiang8727.streamletmultiplatform.domain.transaction.usecase.GetCategoriesByTransactionTypeUseCase
import com.youxiang8727.streamletmultiplatform.domain.transaction.usecase.GetTransactionByIdUseCase
import com.youxiang8727.streamletmultiplatform.domain.transaction.usecase.TransactionScreenReducer
import com.youxiang8727.streamletmultiplatform.domain.transaction.usecase.UpsertTransactionUseCase
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate

class TransactionScreenViewModel(
    private val getTransactionByIdUseCase: GetTransactionByIdUseCase,
    private val upsertTransactionUseCase: UpsertTransactionUseCase,
    private val getCategoriesByTransactionTypeUseCase: GetCategoriesByTransactionTypeUseCase,
    private val id: Long
): MviViewModel<TransactionScreenUiState, TransactionScreenUiEvent, Nothing>(
    initialState = TransactionScreenUiState(),
    reducer = TransactionScreenReducer()
) {
    private suspend fun initTransactionData() {
        if (id != 0L) {
            val transaction = getTransactionByIdUseCase(id)
            dispatch(
                TransactionScreenUiEvent.OnTransactionDataUpdated(
                    transactionData = transaction
                )
            )
        } else {
            val categories = getCategoriesByTransactionTypeUseCase(state.value.transactionType)
            dispatch(
                TransactionScreenUiEvent.OnTransactionTypeChanged(
                    transactionType = state.value.transactionType,
                    categories = categories,
                    selectedCategory = getDefaultCategory(state.value.transactionType).toCategory()
                )
            )
        }
    }

    fun onTransactionTypeChanged(transactionType: TransactionType) {
        if (transactionType == state.value.transactionType) return
        viewModelScope.launch {
            val categories = getCategoriesByTransactionTypeUseCase(transactionType)
            dispatch(
                TransactionScreenUiEvent.OnTransactionTypeChanged(
                    transactionType = transactionType,
                    categories = categories,
                    selectedCategory = getDefaultCategory(transactionType).toCategory()
                )
            )
        }
    }

    fun onDateSelected(date: LocalDate) {
        dispatch(
            TransactionScreenUiEvent.OnDateSelected(date = date)
        )
    }

    fun onTitleChanged(title: String) {
        if (title.length == 10) return
        dispatch(
            TransactionScreenUiEvent.OnTitleChanged(title = title)
        )
    }

    fun onCategoryChanged(category: Category) {
        if (category == state.value.category) return
        dispatch(
            TransactionScreenUiEvent.OnCategoryChanged(category = category)
        )
    }

    fun onAmountChanged(amountString: String) {
        val amount = amountString.toDoubleOrNull() ?: 0.0
        if (amount >= TransactionScreenUiState.MAX_AMOUNT) return
        val amountString = if (amount == 0.0) "" else amount.toString()
        dispatch(
            TransactionScreenUiEvent.OnAmountChanged(amountString)
        )
    }

    fun onDescriptionChanged(description: String) {
        if (description.length == TransactionScreenUiState.MAX_DESCRIPTION_LENGTH) return
        dispatch(
            TransactionScreenUiEvent.OnDescriptionChanged(description = description)
        )
    }

    fun onSaveClicked() {
        val transactionData = state.value.toTransactionData()
        viewModelScope.launch {
            upsertTransactionUseCase(
                transactionData = transactionData
            )
        }
        dispatch(
            TransactionScreenUiEvent.OnSaveTransaction
        )
    }

    init {
        viewModelScope.launch {
            initTransactionData()
        }
    }
}