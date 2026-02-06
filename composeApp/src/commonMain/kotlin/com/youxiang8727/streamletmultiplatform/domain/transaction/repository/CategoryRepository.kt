package com.youxiang8727.streamletmultiplatform.domain.transaction.repository

import com.youxiang8727.streamletmultiplatform.data.db.model.CategoryEntity
import com.youxiang8727.streamletmultiplatform.domain.transaction.model.Category
import com.youxiang8727.streamletmultiplatform.domain.transaction.model.TransactionType
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun getCategoriesCount(): Int

    suspend fun insertAll(categories: List<Category>)

    suspend fun upsert(category: Category)

    fun getAllCategories(): Flow<List<Category>>

    suspend fun getCategoriesByTransactionType(transactionType: TransactionType): List<Category>
}