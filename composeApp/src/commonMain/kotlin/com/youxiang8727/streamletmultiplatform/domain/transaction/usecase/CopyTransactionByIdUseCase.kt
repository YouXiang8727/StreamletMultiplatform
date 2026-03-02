package com.youxiang8727.streamletmultiplatform.domain.transaction.usecase

import com.youxiang8727.streamletmultiplatform.domain.transaction.repository.TransactionRepository

class CopyTransactionByIdUseCase(
    private val transactionRepository: TransactionRepository
) {
    suspend operator fun invoke(
        id: Long
    ) {
        val transactionData = transactionRepository.getTransactionById(id)
            .copy(
                id = 0L
            )
        transactionRepository.upsert(transactionData)
    }
}