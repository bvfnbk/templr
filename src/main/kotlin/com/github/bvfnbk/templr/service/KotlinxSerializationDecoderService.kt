package com.github.bvfnbk.templr.service

import com.github.bvfnbk.templr.api.model.*
import com.github.bvfnbk.templr.api.service.DecoderService
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.*
import java.io.InputStreamReader

/**
 * @author bvfnbk
 */
class KotlinxSerializationDecoderService : DecoderService {
    override fun decode(reader: InputStreamReader): Model<*> = mapElement(decodeToJsonElement(reader))

    private fun mapElement(element: JsonElement): Model<*> = when (element) {
        is JsonObject, is JsonArray -> decodeElement(element)
        else -> throw IllegalArgumentException("Given model is no JSON object.")
    }

    private fun decodeToJsonElement(reader: InputStreamReader): JsonElement = reader.use {
        return Json.decodeFromString(it.readText())
    }

    private fun decodeElement(element: JsonElement): Model<*> = when (element) {
        is JsonObject -> ObjectModel(element.entries.map { (key, value) -> Pair(key, decodeElement(value)) }.toMap())
        is JsonArray -> ObjectModel(
            mapOf(
                "elementList" to ListModel(
                    element.map { listElement -> mapElement(listElement) }
                )
            )
        )
        is JsonPrimitive -> mapPrimitive(element.content)
    }

    private fun mapPrimitive(value: String): Model<*> = when {
        value == "null" -> throw IllegalArgumentException("Got null value.")
        value == "true" -> BooleanModel(true)
        value == "false" -> BooleanModel(false)
        isNumber(value) -> NumberModel(value.toDouble())
        else -> StringModel(value)
    }

    private fun isNumber(value: String): Boolean = try {
        value.toDouble()
        true
    } catch (formatError: NumberFormatException) {
        false
    }
}
