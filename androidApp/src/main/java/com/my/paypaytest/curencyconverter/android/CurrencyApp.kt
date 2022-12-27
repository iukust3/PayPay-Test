package com.my.paypaytest.curencyconverter.android

import android.app.Application
import android.util.Log.d
import com.my.paypaytest.curencyconverter.android.di.appModule
import com.my.paypaytest.curencyconverter.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.ext.koin.androidLogger
import co.touchlab.kermit.Logger
import org.koin.core.logger.Level
import org.koin.core.logger.MESSAGE

class CurrencyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(true) {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)

            androidContext(this@CurrencyApp)
            modules(appModule)
        }
        Logger.d { "CurrencyApp" }

    }
}