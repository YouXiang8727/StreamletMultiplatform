package com.youxiang8727.streamletmultiplatform.data.db.repository

import com.youxiang8727.streamletmultiplatform.data.db.dao.TransactionEntityDao
import com.youxiang8727.streamletmultiplatform.data.db.pojo.toTransactionData
import com.youxiang8727.streamletmultiplatform.domain.transaction.model.TransactionData
import com.youxiang8727.streamletmultiplatform.domain.transaction.model.toTransactionEntity
import com.youxiang8727.streamletmultiplatform.domain.transaction.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlinx.datetime.LocalDate
import kotlin.coroutines.CoroutineContext

class TransactionRepositoryImpl(
    private val transactionEntityDao: TransactionEntityDao,
    private val coroutineContext: CoroutineContext
): TransactionRepository {
    override fun getTransactionByDate(date: LocalDate): Flow<List<TransactionData>> {
        return transactionEntityDao.getTransactionsByDate(date)
            .map { pojos ->
                pojos.map { pojo ->
                    pojo.toTransactionData()
                }
            }.flowOn(coroutineContext)
    }

    override suspend fun getTransactionById(id: Long): TransactionData {
        return withContext(coroutineContext) {
            transactionEntityDao.getTransactionById(id)
                .toTransactionData()
        }
    }

    override suspend fun upsert(transactionData: TransactionData) {
        withContext(coroutineContext) {
            val transactionEntity = transactionData.toTransactionEntity()
            transactionEntityDao.upsert(transactionEntity)
        }
    }

    override suspend fun deleteTransactionById(id: Long) {
        withContext(coroutineContext) {
            transactionEntityDao.deleteTransactionById(id)
        }
    }
}