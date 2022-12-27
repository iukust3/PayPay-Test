package com.my.paypaytest.curencyconverter

import android.app.Application
import io.ktor.client.engine.android.*
import org.koin.core.module.Module
import org.koin.dsl.module

class AndroidPlatform : Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()
actual fun platformModule()= module {
    single { Android.create() }
}

actual typealias KMMContext = Application