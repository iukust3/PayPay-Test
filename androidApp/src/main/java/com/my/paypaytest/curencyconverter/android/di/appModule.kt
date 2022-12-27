package com.my.paypaytest.curencyconverter.android.di

import android.app.Application
import com.my.paypaytest.curencyconverter.KMMContext
import com.my.paypaytest.curencyconverter.android.viewModels.CurrencyListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.logger.Level
import org.koin.dsl.module

val appModule= module {
    viewModel { CurrencyListViewModel(get  ()) }
}