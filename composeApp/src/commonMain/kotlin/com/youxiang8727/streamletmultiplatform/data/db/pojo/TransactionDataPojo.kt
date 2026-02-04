package com.youxiang8727.streamletmultiplatform.data.db.pojo

import androidx.room.Embedded
import androidx.room.Relation
import com.youxiang8727.streamletmultiplatform.core.ui.UiText
import com.youxiang8727.streamletmultiplatform.data.db.model.CategoryEntity
import com.youxiang8727.streamletmultiplatform.data.db.model.TransactionEntity
import com.youxiang8727.streamletmultiplatform.domain.transaction.model.DefaultCategory
import com.youxiang8727.streamletmultiplatform.ui.home.TransactionItemUiState

data class TransactionDataPojo(
    @Embedded val transactionEntity: TransactionEntity,
    @Relation(
        parentColumn = "categoryId",
        entityColumn = "id"
    )
    val categoryEntity: CategoryEntity
)

fun TransactionDataPojo.toTransactionItemUiState(): TransactionItemUiState = TransactionItemUiState(
    id = transactionEntity.id,
    title = transactionEntity.title,
    amount = transactionEntity.amount,
    categoryName = if (categoryEntity.default) {
        UiText.StringResource(
            DefaultCategory.valueOf(categoryEntity.name).stringResource
        )
    } else {
        UiText.DynamicString(categoryEntity.name)
    }
)
