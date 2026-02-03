package com.youxiang8727.streamletmultiplatform.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import com.youxiang8727.streamletmultiplatform.data.db.model.TransactionEntity

@Dao
interface TransactionEntityDao {
    @Insert
    suspend fun insert(transactionEntity: TransactionEntity)


}