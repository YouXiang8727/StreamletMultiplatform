package com.youxiang8727.streamletmultiplatform.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.youxiang8727.streamletmultiplatform.data.db.model.CategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryEntityDao {
    @Query("SELECT count(*) FROM categories")
    suspend fun getCount(): Int

    @Insert
    suspend fun insertAll(categoryEntities: List<CategoryEntity>)

    @Upsert
    suspend fun upsert(categoryEntity: CategoryEntity)

    @Query("SELECT * FROM categories")
    fun getAllCategories(): Flow<List<CategoryEntity>>
}