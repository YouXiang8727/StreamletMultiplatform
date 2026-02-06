package com.youxiang8727.streamletmultiplatform.data.db.repository

import com.youxiang8727.streamletmultiplatform.data.db.dao.CategoryEntityDao
import com.youxiang8727.streamletmultiplatform.data.db.model.CategoryEntity
import com.youxiang8727.streamletmultiplatform.data.db.model.toCategory
import com.youxiang8727.streamletmultiplatform.domain.transaction.model.Category
import com.youxiang8727.streamletmultiplatform.domain.transaction.model.TransactionType
import com.youxiang8727.streamletmultiplatform.domain.transaction.model.toCategoryEntity
import com.youxiang8727.streamletmultiplatform.domain.transaction.repository.CategoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class CategoryRepositoryImpl(
    private val categoryEntityDao: CategoryEntityDao
): CategoryRepository {
    override suspend fun getCategoriesCount(): Int {
        return categoryEntityDao.getCount()
    }

    override suspend fun insertAll(categories: List<Category>) {
        val categoryEntities = categories.map { it.toCategoryEntity() }
        categoryEntityDao.insertAll(categoryEntities)
    }

    override suspend fun upsert(category: Category) {
        categoryEntityDao.upsert(category.toCategoryEntity())
    }

    override fun getAllCategories(): Flow<List<Category>> {
        return categoryEntityDao.getAllCategories().map { categoryEntities ->
            categoryEntities.map { entity ->
                entity.toCategory()
            }
        }
    }

    override suspend fun getCategoriesByTransactionType(transactionType: TransactionType): List<Category> {
        return withContext(Dispatchers.IO) {
            categoryEntityDao.getCategoriesByTransactionType(transactionType).map {
                it.toCategory()
            }
        }
    }
}