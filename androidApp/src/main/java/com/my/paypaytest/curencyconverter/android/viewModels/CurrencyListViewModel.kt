package com.my.paypaytest.curencyconverter.android.viewModels

import androidx.lifecycle.ViewModel
import com.my.paypaytest.curencyconverter.repository.CurrencyRepository

data class CurrencyListUiState(
    val items: Map<String,String> = HashMap(),
    val isLoading: Boolean = false,
    val userMessage: Int? = null
)
class CurrencyListViewModel(private  val repository: CurrencyRepository): ViewModel() {
    val uiState=CurrencyListUiState()
    fun refresh(){

    }
}