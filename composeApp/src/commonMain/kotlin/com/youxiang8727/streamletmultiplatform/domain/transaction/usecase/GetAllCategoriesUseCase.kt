package com.youxiang8727.streamletmultiplatform.domain.transaction.usecase

import com.youxiang8727.streamletmultiplatform.data.db.model.CategoryEntity
import com.youxiang8727.streamletmultiplatform.domain.transaction.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow

class GetAllCategoriesUseCase(
    private val categoryRepository: CategoryRepository
) {
    operator fun invoke(): Flow<List<CategoryEntity>> {
        return categoryRepository.getAllCategories()
    }
}