package com.my.paypaytest.curencyconverter

import com.my.paypaytest.curencyconverter.di.commonModule
import com.my.paypaytest.curencyconverter.remote.CurrencyApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime

class ApiTest:KoinTest {
    private val repo : CurrencyApi by inject()

    @OptIn(ExperimentalCoroutinesApi::class)
    @BeforeTest
    fun setUp()  {
        Dispatchers.setMain(StandardTestDispatcher())
        startKoin{
            modules(
                commonModule(true),
                platformModule()
            )
        }
    }
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testGetCurrencyList() = runTest {
        val result = repo.getCurrenciesListTest()
        println(result)
        assertTrue(result.isNotEmpty())
    }
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testConverter() = runTest {
        val result = repo.convert("USD","PKR",20.0)
        println(result.toString())
        assertTrue(result.success)
    }
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testApiKeyIfNotAdded() = runTest {
        val result = repo.getCurrenciesListTestWithNoApiKey()
        println(result.toString())
        assertTrue(result.contains("No API key found in request"))
    }
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testWrongRoute() = runTest {
        val result = repo.getCurrenciesListTestWrongRoute()
        println(result.toString())
        assertTrue(result.contains("no Route matched with those values"))
    }


}