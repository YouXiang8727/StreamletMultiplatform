package com.youxiang8727.streamletmultiplatform.data.db

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import com.youxiang8727.streamletmultiplatform.data.db.converter.CategoryEntityConverter
import com.youxiang8727.streamletmultiplatform.data.db.converter.TransactionEntityConverter
import com.youxiang8727.streamletmultiplatform.data.db.dao.CategoryEntityDao
import com.youxiang8727.streamletmultiplatform.data.db.dao.TransactionEntityDao
import com.youxiang8727.streamletmultiplatform.data.db.model.CategoryEntity
import com.youxiang8727.streamletmultiplatform.data.db.model.TransactionEntity

const val DB_FILE_NAME = "streamlet.db"

@Database(
    entities = [TransactionEntity::class, CategoryEntity::class],
    version = 1
)
@TypeConverters(
    TransactionEntityConverter::class,
    CategoryEntityConverter::class
)
@ConstructedBy(StreamletDatabaseConstructor::class)
abstract class StreamletDatabase: RoomDatabase() {
    abstract fun transactionEntityDao(): TransactionEntityDao
    abstract fun categoryEntityDao(): CategoryEntityDao
}

@Suppress("KotlinNoActualForExpect")
expect object StreamletDatabaseConstructor: RoomDatabaseConstructor<StreamletDatabase> {
    override fun initialize(): StreamletDatabase
}

expect class StreamletDatabaseFactory() {
    fun create(): StreamletDatabase
}

val DATABASE_ON_CREATE_CALLBACK = object: RoomDatabase.Callback() {
    override fun onCreate(connection: SQLiteConnection) {
        super.onCreate(connection)
        connection.execSQL("INSERT OR REPLACE INTO sqlite_sequence (name, seq) VALUES ('categories', 1000)")
    }
}
