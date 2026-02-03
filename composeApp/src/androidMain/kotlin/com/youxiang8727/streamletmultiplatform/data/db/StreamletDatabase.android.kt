package com.youxiang8727.streamletmultiplatform.data.db

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.youxiang8727.streamletmultiplatform.StreamletApplication
import kotlinx.coroutines.Dispatchers

actual class StreamletDatabaseFactory {
    actual fun create(): StreamletDatabase {
        val dbFile = StreamletApplication.instance.getDatabasePath(DB_FILE_NAME)
        return Room
            .databaseBuilder<StreamletDatabase>(
                context = StreamletApplication.instance,
                name = dbFile.absolutePath,
            ).setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }
}