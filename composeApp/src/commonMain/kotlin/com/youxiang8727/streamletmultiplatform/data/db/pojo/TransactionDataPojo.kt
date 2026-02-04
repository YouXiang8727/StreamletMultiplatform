package com.youxiang8727.streamletmultiplatform.data.db.pojo

import androidx.room.Embedded
import androidx.room.Relation
import com.youxiang8727.streamletmultiplatform.data.db.model.CategoryEntity
import com.youxiang8727.streamletmultiplatform.data.db.model.TransactionEntity
import com.youxiang8727.streamletmultiplatform.data.db.model.toCategory
import com.youxiang8727.streamletmultiplatform.domain.transaction.model.TransactionData

data class TransactionDataPojo(
    @Embedded val transactionEntity: TransactionEntity,
    @Relation(
        parentColumn = "categoryId",
        entityColumn = "id"
    )
    val categoryEntity: CategoryEntity
)

fun TransactionDataPojo.toTransactionData(): TransactionData = TransactionData(
    id = transactionEntity.id,
    title = transactionEntity.title,
    date = transactionEntity.date,
    amount = transactionEntity.amount,
    description = transactionEntity.description,
    category = categoryEntity.toCategory()
)