package com.youxiang8727.streamletmultiplatform.domain.transaction.repository

import com.youxiang8727.streamletmultiplatform.data.db.model.CategoryEntity
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun getCategoriesCount(): Int

    suspend fun insertAll(categoryEntities: List<CategoryEntity>)

    suspend fun upsert(categoryEntity: CategoryEntity)

    fun getAllCategories(): Flow<List<CategoryEntity>>
}