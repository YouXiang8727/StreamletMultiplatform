package com.youxiang8727.streamletmultiplatform.ui.category.list

import com.youxiang8727.streamletmultiplatform.core.mvi.UiEffect
import com.youxiang8727.streamletmultiplatform.domain.transaction.model.Category

sealed interface CategoriesScreenUiEffect: UiEffect {
    data class NavigateToEditCategoryScreen(
        val id: Long
    ): CategoriesScreenUiEffect

    data class ShowDefaultCategoryProtectedMessage(
        val category: Category
    ): CategoriesScreenUiEffect
}