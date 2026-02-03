package com.youxiang8727.streamletmultiplatform.data.db.converter

import androidx.room.TypeConverter
import com.youxiang8727.streamletmultiplatform.domain.transaction.model.TransactionType

class CategoryEntityConverter {
    @TypeConverter
    fun fromTransactionType(transactionType: TransactionType): String {
        return transactionType.name
    }

    @TypeConverter
    fun toTransactionType(value: String): TransactionType {
        return TransactionType.valueOf(value)
    }
}