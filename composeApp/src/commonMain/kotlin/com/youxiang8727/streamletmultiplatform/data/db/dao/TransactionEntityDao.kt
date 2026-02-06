package com.youxiang8727.streamletmultiplatform.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.youxiang8727.streamletmultiplatform.data.db.model.TransactionEntity
import com.youxiang8727.streamletmultiplatform.data.db.pojo.TransactionDataPojo
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate

@Dao
interface TransactionEntityDao {
    @Upsert
    suspend fun upsert(transactionEntity: TransactionEntity)

    @Transaction
    @Query("SELECT * FROM `transaction` WHERE date = :date")
    fun getTransactionsByDate(date: LocalDate): Flow<List<TransactionDataPojo>>

    @Transaction
    @Query("SELECT * FROM `transaction` WHERE id = :id")
    suspend fun getTransactionById(id: Long): TransactionDataPojo
}