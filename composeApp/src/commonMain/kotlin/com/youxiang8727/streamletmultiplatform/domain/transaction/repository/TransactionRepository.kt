package com.youxiang8727.streamletmultiplatform.domain.transaction.repository

import com.youxiang8727.streamletmultiplatform.domain.transaction.model.TransactionData
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate

interface TransactionRepository {
    fun getTransactionByDate(date: LocalDate): Flow<List<TransactionData>>

    suspend fun getTransactionById(id: Long): TransactionData

    suspend fun upsert(transactionData: TransactionData)
}