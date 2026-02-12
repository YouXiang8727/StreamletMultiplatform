package com.youxiang8727.streamletmultiplatform.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import okio.Path.Companion.toPath

const val SETTINGS_DATA_STORE_FILE_NAME = "settings.preferences_pb"

fun createDataStore(producePath: () -> String): DataStore<Preferences> = PreferenceDataStoreFactory.createWithPath(
    produceFile = {
        producePath().toPath()
    }
)

expect fun createDataStoreByPlatform(): DataStore<Preferences>