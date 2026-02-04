package com.youxiang8727.streamletmultiplatform.domain.transaction.usecase

import com.youxiang8727.streamletmultiplatform.domain.transaction.model.TransactionData
import com.youxiang8727.streamletmultiplatform.domain.transaction.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate

class GetTransactionByDateUseCase(
    private val transactionRepository: TransactionRepository
) {
    operator fun invoke(date: LocalDate): Flow<List<TransactionData>> {
        return transactionRepository.getTransactionByDate(date)
    }
}