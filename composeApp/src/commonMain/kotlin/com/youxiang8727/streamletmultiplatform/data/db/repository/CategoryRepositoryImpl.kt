package com.youxiang8727.streamletmultiplatform.data.db.repository

import com.youxiang8727.streamletmultiplatform.data.db.dao.CategoryEntityDao
import com.youxiang8727.streamletmultiplatform.data.db.model.toCategory
import com.youxiang8727.streamletmultiplatform.domain.transaction.model.Category
import com.youxiang8727.streamletmultiplatform.domain.transaction.model.TransactionType
import com.youxiang8727.streamletmultiplatform.domain.transaction.model.toCategoryEntity
import com.youxiang8727.streamletmultiplatform.domain.transaction.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class CategoryRepositoryImpl(
    private val categoryEntityDao: CategoryEntityDao,
    private val coroutineContext: CoroutineContext
): CategoryRepository {
    override suspend fun getCategoriesCount(): Int {
        return withContext(coroutineContext) {
            categoryEntityDao.getCount()
        }
    }

    override suspend fun insertAll(categories: List<Category>) {
        withContext(coroutineContext) {
            val categoryEntities = categories.map { it.toCategoryEntity() }
            categoryEntityDao.insertAll(categoryEntities)
        }
    }

    override suspend fun upsert(category: Category) {
        withContext(coroutineContext) {
            categoryEntityDao.upsert(category.toCategoryEntity())
        }
    }

    override fun getAllCategories(): Flow<List<Category>> {
        return categoryEntityDao.getAllCategories().map { categoryEntities ->
            categoryEntities.map { entity ->
                entity.toCategory()
            }
        }.flowOn(coroutineContext)
    }

    override suspend fun getCategoriesByTransactionType(transactionType: TransactionType): List<Category> {
        return withContext(coroutineContext) {
            categoryEntityDao.getCategoriesByTransactionType(transactionType).map {
                it.toCategory()
            }
        }
    }

    override suspend fun getCategoryById(id: Long): Category {
        return withContext(coroutineContext) {
            categoryEntityDao.getCategoryById(id).toCategory()
        }
    }

    override suspend fun getCategoryByTransactionTypeAndName(transactionType: TransactionType, name: String): Category? {
        return withContext(coroutineContext) {
            categoryEntityDao.getCategoryByTransactionTypeAndName(transactionType = transactionType, name = name)?.toCategory()
        }
    }
}