package com.youxiang8727.streamletmultiplatform.ui.category.edit

import com.youxiang8727.streamletmultiplatform.core.mvi.UiEffect

interface EditCategoryScreenUiEffect: UiEffect {
    data object OnDataSaved: EditCategoryScreenUiEffect
}