package com.youxiang8727.streamletmultiplatform.domain.transaction.usecase

import com.youxiang8727.streamletmultiplatform.domain.transaction.model.TransactionData
import com.youxiang8727.streamletmultiplatform.domain.transaction.repository.TransactionRepository

class UpsertTransactionUseCase(
    private val transactionRepository: TransactionRepository
) {
    suspend operator fun invoke(transactionData: TransactionData) {
        transactionRepository.upsert(transactionData)
    }
}