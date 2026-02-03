package com.youxiang8727.streamletmultiplatform.domain.transaction.usecase

import com.youxiang8727.streamletmultiplatform.data.db.model.CategoryEntity
import com.youxiang8727.streamletmultiplatform.domain.transaction.model.DefaultCategory
import com.youxiang8727.streamletmultiplatform.domain.transaction.repository.CategoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class InsertDefaultCategoriesUseCase(
    private val categoryRepository: CategoryRepository
) {
    suspend operator fun invoke() {
        withContext(Dispatchers.IO) {
            if (categoryRepository.getCategoriesCount() == 0) {
                categoryRepository.insertAll(
                    DefaultCategory.entries.map { defaultCategory ->
                        CategoryEntity(defaultCategory)
                    }
                )
            }
        }

    }
}