package com.youxiang8727.streamletmultiplatform.data.db.pojo

import androidx.room.Embedded
import androidx.room.Relation
import com.youxiang8727.streamletmultiplatform.data.db.model.CategoryEntity
import com.youxiang8727.streamletmultiplatform.data.db.model.TransactionEntity

data class TransactionDataPojo(
    @Embedded val transactionEntity: TransactionEntity,
    @Relation(
        parentColumn = "categoryId",
        entityColumn = "id"
    )
    val categoryEntity: CategoryEntity
)
