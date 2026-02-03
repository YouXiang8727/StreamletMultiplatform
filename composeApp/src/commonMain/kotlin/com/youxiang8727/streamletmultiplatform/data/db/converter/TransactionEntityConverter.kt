package com.youxiang8727.streamletmultiplatform.data.db.converter

import androidx.room.TypeConverter
import kotlinx.datetime.LocalDate

class TransactionEntityConverter {
    @TypeConverter
    fun fromLocalDate(date: LocalDate): Long {
        return date.toEpochDays()
    }

    @TypeConverter
    fun toLocalDate(epochDays: Long): LocalDate {
        return LocalDate.fromEpochDays(epochDays)
    }
}