package com.my.paypaytest.curencyconverter.android.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.my.paypaytest.curencyconverter.repository.CurrencyRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class CurrencyListUiState(
    val items: Map<String, String> = HashMap(),
    val isLoading: Boolean = false,
    val userMessage: Int? = null
)

class CurrencyListViewModel(private val repository: CurrencyRepository) : ViewModel() {
    val uiState = repository.getCurrencyAsFlowList().map {
        CurrencyListUiState(it)
    }.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000),
        CurrencyListUiState(isLoading = true)
    )

    fun refresh() {
        viewModelScope.launch {
            repository.getCurrencyList();
        }
    }
}