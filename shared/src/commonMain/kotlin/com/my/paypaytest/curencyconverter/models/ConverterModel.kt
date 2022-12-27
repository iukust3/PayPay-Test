package com.my.paypaytest.curencyconverter.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConverterModel(
    var info: Info,
    var query: Query,
    var result: Double,
    var success: Boolean
) {
    @Serializable
    data class Info(
        var quote: Double,
        var timestamp: Int
    )

    @Serializable
    data class Query(
        var amount: Int,
        var from: String,
        var to: String
    )
}