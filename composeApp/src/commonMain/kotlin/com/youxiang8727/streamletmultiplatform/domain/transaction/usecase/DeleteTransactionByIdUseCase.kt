package com.youxiang8727.streamletmultiplatform.domain.transaction.usecase

import com.youxiang8727.streamletmultiplatform.domain.transaction.repository.TransactionRepository

class DeleteTransactionByIdUseCase(
    private val transactionRepository: TransactionRepository
) {
    suspend operator fun invoke(
        id: Long,
    ) {
        transactionRepository.deleteTransactionById(id)
    }
}