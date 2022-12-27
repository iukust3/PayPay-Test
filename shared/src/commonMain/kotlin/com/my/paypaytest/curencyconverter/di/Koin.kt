package com.my.paypaytest.curencyconverter.di

import com.my.paypaytest.curencyconverter.KMMContext
import com.my.paypaytest.curencyconverter.platformModule
import com.my.paypaytest.curencyconverter.remote.CurrencyApi
import com.my.paypaytest.curencyconverter.repository.CurrencyRepository
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.koinApplication
import org.koin.dsl.module

fun initKoin(enableNetworkLogs: Boolean = false, appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
         appDeclaration()

        modules(commonModule(enableNetworkLogs = enableNetworkLogs), platformModule())
    }

fun commonModule(enableNetworkLogs: Boolean) = module {
     single { createJson() }
    single { createHttpClient(get(), get(), enableNetworkLogs = enableNetworkLogs) }

    single { CoroutineScope(Dispatchers.Default + SupervisorJob()) }

    single { CurrencyApi(get()) }

    single { CurrencyRepository(get(),get()) }

}

fun createJson() = Json { isLenient = true; ignoreUnknownKeys = true }


fun createHttpClient(httpClientEngine: HttpClientEngine, json: Json, enableNetworkLogs: Boolean) =
    HttpClient(httpClientEngine) {
        install(ContentNegotiation) {
            json(json)
        }
        if (enableNetworkLogs) {
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.INFO
            }
        }
    }