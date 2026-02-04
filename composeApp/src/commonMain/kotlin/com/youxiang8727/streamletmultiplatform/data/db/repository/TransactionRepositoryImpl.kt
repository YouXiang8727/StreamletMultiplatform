package com.youxiang8727.streamletmultiplatform.data.db.repository

import com.youxiang8727.streamletmultiplatform.data.db.dao.TransactionEntityDao
import com.youxiang8727.streamletmultiplatform.data.db.pojo.TransactionDataPojo
import com.youxiang8727.streamletmultiplatform.domain.transaction.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate

class TransactionRepositoryImpl(
    private val transactionEntityDao: TransactionEntityDao
): TransactionRepository {
    override fun getTransactionByDate(date: LocalDate): Flow<List<TransactionDataPojo>> {
        return transactionEntityDao.getTransactionsByDate(date)
    }
}