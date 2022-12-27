package com.my.paypaytest.curencyconverter.remote

import com.my.paypaytest.curencyconverter.KMMContext
import com.my.paypaytest.curencyconverter.actuals.putString
import com.my.paypaytest.curencyconverter.models.ConverterModel
import com.my.paypaytest.curencyconverter.models.CurrencyList
import com.my.paypaytest.curencyconverter.utils.PrefKey
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.datetime.Clock
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CurrencyApi(private val client: HttpClient) : KoinComponent {
    val kmmContext: KMMContext by inject();
    suspend fun getCurrenciesList(): Map<String, String> {
        val response = client.get("$baseUrl/list?") {
            openLayerConfig()
        }.bodyAsText()
        var list=Json.decodeFromString<CurrencyList>(response)
        kmmContext.putString(PrefKey.lastUpdate, Clock.System.now().toString())
        kmmContext.putString(PrefKey.currencies, Json.encodeToString(list.currencies))
        return list.currencies as Map<String, String>  ;
    }

    suspend fun getCurrenciesListTest(): Map<String, String> {
        val response = client.get("$baseUrl/list?") {
            openLayerConfig()
        }.body<CurrencyList>()
        println("Json string " + Json.encodeToString(response.currencies))
        return response.currencies as Map<String, String>;
    }

    suspend fun getCurrenciesListTestWithNoApiKey(): String {
        val response = client.get("$baseUrl/list?") {
            baseConfiguration()
        }.bodyAsText()
        println("Json string $response")
        return response;
    }
    suspend fun getCurrenciesListTestWrongRoute(): String {
        val response = client.get("$baseUrl/?") {
            baseConfiguration()
        }.bodyAsText()
        println("Json string $response")
        return response;
    }
    suspend fun convert(source: String?, destentaion: String?, amount: Double): ConverterModel {
        val response = client.get("${baseUrl}/convert?to=$destentaion&from=$source&amount=$amount") {
            openLayerConfig()
        }.body<ConverterModel>()
        return response;
    }
}

private val baseUrl = "https://api.apilayer.com/currency_data"
private val appId: String = "9ad3814801de4663846026d7514eaa71"
private val content = "application/json;charset=utf-8"

private fun HttpRequestBuilder.baseConfiguration() {
    headers {
        append("Accept", content)
    }
    parameter("app_id", appId)
}
private fun HttpRequestBuilder.openLayerConfig() {
    headers {
        append("Accept", content)
    }
    parameter("apikey", "OmbmZ7oMICrdGUuhGx44Jy6pMwaBUqUI")
}

