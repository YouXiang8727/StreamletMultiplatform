package com.youxiang8727.streamletmultiplatform.data.db.repository

import com.youxiang8727.streamletmultiplatform.data.db.dao.CategoryEntityDao
import com.youxiang8727.streamletmultiplatform.data.db.model.CategoryEntity
import com.youxiang8727.streamletmultiplatform.domain.transaction.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow

class CategoryRepositoryImpl(
    private val categoryEntityDao: CategoryEntityDao
): CategoryRepository {
    override suspend fun getCategoriesCount(): Int {
        return categoryEntityDao.getCount()
    }

    override suspend fun insertAll(categoryEntities: List<CategoryEntity>) {
        categoryEntityDao.insertAll(categoryEntities)
    }

    override suspend fun upsert(categoryEntity: CategoryEntity) {
        categoryEntityDao.upsert(categoryEntity)
    }

    override fun getAllCategories(): Flow<List<CategoryEntity>> {
        return categoryEntityDao.getAllCategories()
    }
}