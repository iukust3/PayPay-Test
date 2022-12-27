package com.my.paypaytest.curencyconverter.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrencyList(
    @SerialName("currencies")
    var currencies: Map<String,String>,
    @SerialName("success")
    var success: Boolean
) {
}