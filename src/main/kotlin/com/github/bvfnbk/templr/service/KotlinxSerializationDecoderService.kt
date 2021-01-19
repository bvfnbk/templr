package com.github.bvfnbk.templr.service

import com.github.bvfnbk.templr.api.service.DecoderService
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.*

import java.io.InputStreamReader

/**
 * @author bvfnbk
 */
class KotlinxSerializationDecoderService : DecoderService {
    override fun decode(reader: InputStreamReader): Map<String, Any> = mapElement(decodeToJsonElement(reader))

    @Suppress("UNCHECKED_CAST")
    private fun mapElement(element: JsonElement): Map<String, Any> = when (element) {
        is JsonObject -> decodeElement(element) as Map<String, Any>
        else -> throw IllegalArgumentException("Given model is no JSON object.")
    }

    private fun decodeToJsonElement(reader: InputStreamReader): JsonElement = reader.use {
        return Json.decodeFromString(it.readText())
    }

    private fun decodeElement(element: JsonElement): Any = when (element) {
        is JsonObject -> element.entries.map { (key, value) -> Pair(key, decodeElement(value)) }.toMap()
        is JsonArray -> element.map { listElement -> decodeElement(listElement) }
        is JsonPrimitive -> element.content
    }
}
