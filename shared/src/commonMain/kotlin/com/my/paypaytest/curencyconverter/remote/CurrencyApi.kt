package com.my.paypaytest.curencyconverter.remote

import com.my.paypaytest.curencyconverter.KMMContext
import com.my.paypaytest.curencyconverter.actuals.putString
import com.my.paypaytest.curencyconverter.utils.PrefKey
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.datetime.Clock
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CurrencyApi(private val client: HttpClient) : KoinComponent {
    val kmmContext: KMMContext by inject();
    suspend fun getCurrenciesList(): Map<String, String> {
        val response = client.get("$baseUrl/currencies.json") {
            baseConfiguration()
        }.body<JsonElement>()
        kmmContext.putString(PrefKey.lastUpdate, Clock.System.now().toString())
        kmmContext.putString(PrefKey.currencies, Json.encodeToString(response.jsonObject))
        println("Json string " + Json.encodeToString(response.jsonObject))
        return response.jsonObject.toMap() as Map<String, String>;
    }
}

private val baseUrl = "https://openexchangerates.org/api"
private val appId: String = "9ad3814801de4663846026d7514eaa71"
private val content = "application/json;charset=utf-8"

private fun HttpRequestBuilder.baseConfiguration() {
    headers {
        append("Accept", content)
    }
    parameter("app_id", appId)
}

