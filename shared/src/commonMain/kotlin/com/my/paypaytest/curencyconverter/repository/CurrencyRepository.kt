package com.my.paypaytest.curencyconverter.repository

import com.my.paypaytest.curencyconverter.KMMContext
import com.my.paypaytest.curencyconverter.actuals.getString
import com.my.paypaytest.curencyconverter.models.ConverterModel
import com.my.paypaytest.curencyconverter.remote.CurrencyApi
import com.my.paypaytest.curencyconverter.utils.PrefKey
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.koin.core.component.KoinComponent
import kotlin.time.Duration

class CurrencyRepository(private val kmmContext: KMMContext, val currencyApi: CurrencyApi) :
    KoinComponent {

    suspend fun getCurrencyList(): Map<String, String> {
        val lastUpdate = kmmContext.getString(PrefKey.lastUpdate);
        val getFromShesrdPref = kmmContext.getString(PrefKey.currencies)
        println("Last Updated $lastUpdate");
        if(lastUpdate!="") {
            val instantInThePast: Instant = Instant.parse(lastUpdate!!)
            val now = Clock.System.now()
            val durationSinceThen: Duration = now - instantInThePast


            println(" json String  $getFromShesrdPref");
            println("Last Updated $durationSinceThen");
        }
        return if (getFromShesrdPref != "") {
         val json=   Json.decodeFromString<JsonElement>("""$getFromShesrdPref""")
        val map=  json.jsonObject.entries.associate { key-> key.key to key.value.jsonPrimitive.content }

            map
        }
        else  {
            currencyApi.getCurrenciesList()
        };
    }
     fun getCurrencyAsFlowList(): Flow<Map<String, String>> {

        val lastUpdate = kmmContext.getString(PrefKey.lastUpdate);
        println("Last Updated $lastUpdate");
         if(lastUpdate!="") {
             val instantInThePast: Instant = Instant.parse(lastUpdate!!)
             val now = Clock.System.now()
             val durationSinceThen: Duration = now - instantInThePast
             if(durationSinceThen.inWholeMinutes>=30){
                 val response=   runBlocking {
                     currencyApi.getCurrenciesList()
                 }
              return   flowOf(response)
             }

         }
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

    fun convert(source: String?, destentaion: String?, amount: Double): ConverterModel{
        println("Conver Source $source Destination $destentaion amount $amount")
        var response= runBlocking(context = CoroutineExceptionHandler(){context,erro1->
            println("Response ${erro1.message}")

        }) {
            currencyApi.convert(source,destentaion,amount)
        }
        println("Response $response")
return  response;
    }
}