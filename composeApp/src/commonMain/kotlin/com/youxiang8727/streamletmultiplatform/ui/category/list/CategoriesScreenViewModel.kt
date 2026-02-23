package com.youxiang8727.streamletmultiplatform.ui.category.list

import androidx.lifecycle.viewModelScope
import com.youxiang8727.streamletmultiplatform.core.mvi.MviViewModel
import com.youxiang8727.streamletmultiplatform.domain.transaction.model.Category
import com.youxiang8727.streamletmultiplatform.domain.transaction.model.TransactionType
import com.youxiang8727.streamletmultiplatform.domain.transaction.usecase.GetAllCategoriesUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CategoriesScreenViewModel(
    getAllCategoriesUseCase: GetAllCategoriesUseCase
): MviViewModel<CategoriesScreenUiState, CategoriesScreenUiEvent, CategoriesScreenUiEffect>(
    initialState = CategoriesScreenUiState(),
    reducer = CategoriesScreenReducer()
) {
    private val allCategoriesFlow = getAllCategoriesUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    private val categoriesFlow = combine(state, allCategoriesFlow) { state, allCategories ->
        allCategories.filter { category ->
            category.type == state.transactionType
        }
    }

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
            categoriesFlow.collect {
                dispatch(
                    CategoriesScreenUiEvent.OnCategoriesChanged(categories = it)
                )
            }
        }
    }
}