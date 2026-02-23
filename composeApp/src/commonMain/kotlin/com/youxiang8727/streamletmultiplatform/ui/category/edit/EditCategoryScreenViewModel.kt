package com.youxiang8727.streamletmultiplatform.ui.category.edit

import androidx.lifecycle.viewModelScope
import com.youxiang8727.streamletmultiplatform.core.mvi.MviViewModel
import com.youxiang8727.streamletmultiplatform.domain.transaction.model.TransactionType
import com.youxiang8727.streamletmultiplatform.domain.transaction.usecase.GetCategoryByIdUseCase
import com.youxiang8727.streamletmultiplatform.domain.transaction.usecase.GetCategoryByTransactionTypeAndNameUseCase
import com.youxiang8727.streamletmultiplatform.domain.transaction.usecase.UpsertCategoryUseCase
import kotlinx.coroutines.launch

class EditCategoryScreenViewModel(
    private val id: Long,
    private val getCategoryByIdUseCase: GetCategoryByIdUseCase,
    private val getCategoryByTransactionTypeAndNameUseCase: GetCategoryByTransactionTypeAndNameUseCase,
    private val upsertCategoryUseCase: UpsertCategoryUseCase
): MviViewModel<EditCategoryScreenUiState, EditCategoryScreenUiEvent, EditCategoryScreenUiEffect>(
    initialState = EditCategoryScreenUiState(),
    reducer = EditCategoryScreenReducer()
) {
    fun onTransactionTypeChanged(transactionType: TransactionType) {
        dispatch(
            EditCategoryScreenUiEvent.OnTransactionTypeChanged(transactionType = transactionType)
        )
    }

    fun onNameChanged(name: String) {
        dispatch(
            EditCategoryScreenUiEvent.OnNameChanged(name = name)
        )
    }

    fun onSaveClicked() {
        viewModelScope.launch {
            val transactionType = state.value.transactionType
            val name = state.value.name

            val duplicatedCategory = getCategoryByTransactionTypeAndNameUseCase(
                transactionType = transactionType,
                name = name
            )

            if (duplicatedCategory != null) {
                dispatch(
                    EditCategoryScreenUiEvent.OnShowCategoryDuplicatedAlertDialog
                )
            } else {
                saveCategory()
            }
        }
    }

    fun saveCategory() {
        viewModelScope.launch {
            val transactionType = state.value.transactionType
            val name = state.value.name
            upsertCategoryUseCase(
                id = id,
                transactionType = transactionType,
                name = name
            )
            dispatch(
                EditCategoryScreenUiEffect.OnDataSaved
            )
        }
    }

    fun dismissCategoryDuplicatedAlertDialog() {
        dispatch(
            EditCategoryScreenUiEvent.OnDismissCategoryDuplicatedAlertDialog
        )
    }

    private suspend fun getCategoryDataById(id: Long) {
        val category = getCategoryByIdUseCase(id)
        dispatch(
            EditCategoryScreenUiEvent.OnDataInitialized(
                id = category.id,
                name = category.name,
                transactionType = category.type
            )
        )
    }

    init {
        viewModelScope.launch {
            if (id == 0L) return@launch
            getCategoryDataById(id = id)
        }
    }
}