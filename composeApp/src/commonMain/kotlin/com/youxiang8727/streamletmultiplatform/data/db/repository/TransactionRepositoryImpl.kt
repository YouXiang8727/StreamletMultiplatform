package com.youxiang8727.streamletmultiplatform.data.db.repository

import com.youxiang8727.streamletmultiplatform.data.db.dao.TransactionEntityDao
import com.youxiang8727.streamletmultiplatform.data.db.pojo.toTransactionData
import com.youxiang8727.streamletmultiplatform.domain.transaction.model.TransactionData
import com.youxiang8727.streamletmultiplatform.domain.transaction.repository.TransactionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.datetime.LocalDate

class TransactionRepositoryImpl(
    private val transactionEntityDao: TransactionEntityDao
): TransactionRepository {
    override fun getTransactionByDate(date: LocalDate): Flow<List<TransactionData>> {
        return transactionEntityDao.getTransactionsByDate(date)
            .map { pojos ->
                pojos.map { pojo ->
                    pojo.toTransactionData()
                }
            }.flowOn(Dispatchers.Default)
    }
}