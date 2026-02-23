package com.youxiang8727.streamletmultiplatform.domain.transaction.usecase

import com.youxiang8727.streamletmultiplatform.domain.transaction.model.Category
import com.youxiang8727.streamletmultiplatform.domain.transaction.model.TransactionType
import com.youxiang8727.streamletmultiplatform.domain.transaction.repository.CategoryRepository

class UpsertCategoryUseCase(
    private val categoryRepository: CategoryRepository
) {
    suspend operator fun invoke(
        id: Long,
        transactionType: TransactionType,
        name: String
    ) {
        val category = Category(
            id = id,
            type = transactionType,
            name = name,
            default = false
        )

        categoryRepository.upsert(category)
    }
}