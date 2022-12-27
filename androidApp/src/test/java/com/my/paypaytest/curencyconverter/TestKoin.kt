package com.my.paypaytest.curencyconverter

import android.app.Application
import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import co.touchlab.kermit.Logger
import com.my.paypaytest.curencyconverter.actuals.getString
import com.my.paypaytest.curencyconverter.android.di.appModule
import com.my.paypaytest.curencyconverter.di.initKoin
import com.my.paypaytest.curencyconverter.repository.CurrencyRepository
import com.my.paypaytest.curencyconverter.utils.PrefKey.currencies
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level
import org.koin.dsl.module
import org.koin.test.check.checkModules
import org.koin.test.mock.MockProviderRule
import org.mockito.Mockito
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest=Config.NONE)
class TestKoinGraph {
    private val context = getApplicationContext<Context>()

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Mockito.mock(clazz.java)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `checking koin modules`() {

        Logger.d { "CurrencyApp" }

        initKoin {

            androidLogger(Level.ERROR)
            androidContext(context)
            modules(appModule)
            module {
                single<KMMContext> { context as Application }
            }
        }.checkModules() {
            var repo:CurrencyRepository=koin.get()
            var kmmContext:KMMContext=koin.get()
            runTest {
                var currency=repo.getCurrencyList();
                println("Get Currency List  $currency")
            }

            println("Get Currency List  "+kmmContext.getString(currencies))
            withInstance<SavedStateHandle>()
        }
    }
}