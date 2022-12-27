package com.my.paypaytest.curencyconverter.utils
/*

object JsonObjectToListMapDeserializer : DeserializationStrategy<LiMap<String, String>>> {

    override val descriptor = SerialClassDescImpl("JsonMap")

    override fun deserialize(decoder: Decoder): Map<String, String> {

        val input = decoder as? JsonInput ?: throw SerializationException("Expected Json Input")
        val array =
            input.decodeJson() as? JsonArray ?: throw SerializationException("Expected JsonArray")

        return array.map {
            it as JsonObject
            val firstKey = it.keys.first()
            firstKey to it[firstKey]!!.content
        }.toMap()


    }

    override fun patch(decoder: Decoder, old: Map<String, String>): Map<String, String> =
        throw UpdateNotSupportedException("Update not supported")

}*/
