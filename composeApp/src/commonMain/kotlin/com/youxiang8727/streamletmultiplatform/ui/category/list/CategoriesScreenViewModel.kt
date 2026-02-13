package com.youxiang8727.streamletmultiplatform.ui.category.list

import androidx.lifecycle.viewModelScope
import com.youxiang8727.streamletmultiplatform.core.mvi.MviViewModel
import com.youxiang8727.streamletmultiplatform.domain.transaction.model.Category
import com.youxiang8727.streamletmultiplatform.domain.transaction.model.TransactionType
import com.youxiang8727.streamletmultiplatform.domain.transaction.usecase.GetCategoriesByTransactionTypeUseCase
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class CategoriesScreenViewModel(
    private val getCategoriesByTransactionTypeUseCase: GetCategoriesByTransactionTypeUseCase
): MviViewModel<CategoriesScreenUiState, CategoriesScreenUiEvent, CategoriesScreenUiEffect>(
    initialState = CategoriesScreenUiState(),
    reducer = CategoriesScreenReducer()
) {
    fun onTransactionTypeChanged(transactionType: TransactionType) {
        if (transactionType == state.value.transactionType) return
        dispatch(
            CategoriesScreenUiEvent.OnTransactionTypeChanged(
                transactionType = transactionType
            )
        )
    }

    fun onCategoryClicked(category: Category) {
        if (category.default) {
            dispatch(
                CategoriesScreenUiEffect.ShowDefaultCategoryProtectedMessage(category = category)
            )
            return
        }

        dispatch(
            CategoriesScreenUiEffect.NavigateToEditCategoryScreen(id = category.id)
        )
    }

    init {
        viewModelScope.launch {
            state.map { it.transactionType }.collect { transactionType ->
                val categories = getCategoriesByTransactionTypeUseCase(transactionType)
                dispatch(
                    CategoriesScreenUiEvent.OnCategoriesChanged(
                        categories = categories
                    )
                )
            }
        }
    }
}