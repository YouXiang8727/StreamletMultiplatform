package com.youxiang8727.streamletmultiplatform

import android.app.Application

class StreamletApplication: Application() {
    companion object {
        lateinit var instance: Application
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}