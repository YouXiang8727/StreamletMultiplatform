package com.youxiang8727.streamletmultiplatform.domain.transaction.model

import com.youxiang8727.streamletmultiplatform.core.ui.UiText
import com.youxiang8727.streamletmultiplatform.data.db.model.CategoryEntity

data class Category(
    val id: Long,
    val name: String,
    val type: TransactionType,
    val default: Boolean
) {
    val categoryUiText: UiText = if (default) {
        UiText.StringResource(
            DefaultCategory.valueOf(name).stringResource
        )
    } else {
        UiText.DynamicString(name)
    }
}

fun Category.toCategoryEntity(): CategoryEntity = CategoryEntity(
    id = id,
    name = name,
    type = type,
    default = default
)
