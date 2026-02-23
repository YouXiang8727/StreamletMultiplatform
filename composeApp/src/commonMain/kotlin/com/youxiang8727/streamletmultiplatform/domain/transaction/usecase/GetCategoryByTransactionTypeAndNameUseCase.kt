package com.youxiang8727.streamletmultiplatform.domain.transaction.usecase

import com.youxiang8727.streamletmultiplatform.domain.transaction.model.Category
import com.youxiang8727.streamletmultiplatform.domain.transaction.model.TransactionType
import com.youxiang8727.streamletmultiplatform.domain.transaction.repository.CategoryRepository

class GetCategoryByTransactionTypeAndNameUseCase(
    private val categoryRepository: CategoryRepository
) {
    suspend operator fun invoke(transactionType: TransactionType, name: String): Category? {
        return categoryRepository.getCategoryByTransactionTypeAndName(
            transactionType = transactionType,
            name = name
        )
    }
}