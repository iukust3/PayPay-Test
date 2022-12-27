package com.my.paypaytest.curencyconverter

import com.my.paypaytest.curencyconverter.di.commonModule
import com.my.paypaytest.curencyconverter.remote.CurrencyApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

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
    fun testGetPeople() = runTest {
        val result = repo.getCurrenciesList()
        println(result)
        assertTrue(result.isNotEmpty())
    }
}