package com.my.paypaytest.curencyconverter

import org.koin.core.module.Module

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect  fun platformModule():Module

expect class KMMContext