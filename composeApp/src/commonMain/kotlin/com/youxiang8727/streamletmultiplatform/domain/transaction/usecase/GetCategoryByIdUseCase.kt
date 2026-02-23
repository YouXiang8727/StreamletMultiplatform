package com.youxiang8727.streamletmultiplatform.domain.transaction.usecase

import com.youxiang8727.streamletmultiplatform.domain.transaction.model.Category
import com.youxiang8727.streamletmultiplatform.domain.transaction.repository.CategoryRepository

class GetCategoryByIdUseCase(
    private val categoryRepository: CategoryRepository
) {
    suspend operator fun invoke(id: Long): Category {
        return categoryRepository.getCategoryById(id)
    }
}