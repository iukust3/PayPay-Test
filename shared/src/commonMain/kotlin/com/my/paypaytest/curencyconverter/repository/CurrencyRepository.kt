package com.my.paypaytest.curencyconverter.repository

import com.my.paypaytest.curencyconverter.KMMContext
import com.my.paypaytest.curencyconverter.actuals.getString
import com.my.paypaytest.curencyconverter.remote.CurrencyApi
import com.my.paypaytest.curencyconverter.utils.PrefKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.jsonObject
import org.koin.core.component.KoinComponent

class CurrencyRepository(private val kmmContext: KMMContext, val currencyApi: CurrencyApi) :
    KoinComponent {

    suspend fun getCurrencyList(): Map<String, String> {
        val lastUpdate = kmmContext.getString(PrefKey.lastUpdate);
        println("Last Updated $lastUpdate");

        val getFromShesrdPref = kmmContext.getString(PrefKey.currencies)
        println(" json String  $getFromShesrdPref");
        return if (getFromShesrdPref != "")
            Json.parseToJsonElement("""$getFromShesrdPref""").jsonObject.toMap() as Map<String, String>;
        else  {
            currencyApi.getCurrenciesList()
        };
    }
     fun getCurrencyAsFlowList(): Flow<Map<String, String>> {

        val lastUpdate = kmmContext.getString(PrefKey.lastUpdate);
        println("Last Updated $lastUpdate");

        val getFromShesrdPref = kmmContext.getString(PrefKey.currencies)
        println(" json String  $getFromShesrdPref");
        return if (getFromShesrdPref != "") {
         flowOf(Json.parseToJsonElement("""$getFromShesrdPref""").jsonObject.toMap() as Map<String, String>);
        } else  {
            val response=   runBlocking {
                currencyApi.getCurrenciesList()
            }
            flowOf(response)
        };
    }
}