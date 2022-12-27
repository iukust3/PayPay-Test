package com.my.paypaytest.curencyconverter

import io.ktor.client.engine.darwin.*
import org.koin.core.module.Module
import org.koin.dsl.module
import platform.darwin.NSObject

actual fun platformModule()= module {
   single {
       Darwin.create()
   }
}

actual typealias KMMContext = NSObject