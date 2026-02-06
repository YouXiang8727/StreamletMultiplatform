package com.youxiang8727.streamletmultiplatform.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.youxiang8727.streamletmultiplatform.domain.transaction.model.Category
import com.youxiang8727.streamletmultiplatform.domain.transaction.model.TransactionType

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "type") val type: TransactionType,
    @ColumnInfo(name = "default") val default: Boolean
)

fun CategoryEntity.toCategory(): Category = Category(
    id = id,
    name = name,
    type = type,
    default = default
)
