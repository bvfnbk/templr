package com.github.bvfnbk.templr.service

import com.github.bvfnbk.templr.api.service.DecoderService
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString

import java.io.InputStreamReader

/**
 * @author bvfnbk
 */
class KotlinxSerializationDecoderService : DecoderService {
    override fun decode(reader: InputStreamReader): Map<*, *> {
        reader.use {
            return Json.decodeFromString(it.readText())
        }
    }
}
