package com.my.paypaytest.curencyconverter.utils

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.json.*

/*

object JsonObjectToListMapDeserializer : DeserializationStrategy<Map<String, String>> {

    override val descriptor = SerialDescriptor("JsonMap",SerialDescriptor.)

    override fun deserialize(decoder: Decoder): Map<String, String> {

        val input = decoder as? JsonDecoder ?: throw SerializationException("Expected Json Input")
        val array =
            input.decodeJsonElement() as? JsonArray ?: throw SerializationException("Expected JsonArray")

        return array.map {
            it as JsonObject
            val firstKey = it.keys.first()
            firstKey to it[firstKey]!!.jsonPrimitive.content
        }.toMap()


    }


}
*/
fun Pair<String,Any>.toStringPair():Pair<String,Any>{
    return try {
        Pair(first, (second as JsonElement).jsonPrimitive.content)
    } catch (e: Exception) {
        return   Pair(first, (second as String))
    }
}
