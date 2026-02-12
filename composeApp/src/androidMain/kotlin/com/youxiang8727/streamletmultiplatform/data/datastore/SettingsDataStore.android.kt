package com.youxiang8727.streamletmultiplatform.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.youxiang8727.streamletmultiplatform.StreamletApplication

actual fun createDataStoreByPlatform(): DataStore<Preferences> {
    return createDataStore {
        StreamletApplication.instance.filesDir.resolve(SETTINGS_DATA_STORE_FILE_NAME).absolutePath
    }
}