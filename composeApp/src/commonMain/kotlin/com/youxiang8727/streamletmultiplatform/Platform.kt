package com.youxiang8727.streamletmultiplatform

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform