package com.github.bvfnbk.templr.api.service

import java.io.File
import java.nio.charset.Charset

/**
 * @author bvfnbk
 */
interface ModelService {
    /**
     * Loads the model from the given abstract path.
     * @param file The file from which the model is supposed to be loaded.
     * @param charset The source character encoding; default: [Charsets.UTF_8]
     * @return the model as a [Map]
     * @throws [IllegalArgumentException] if the given [File] does not exist, is a directory rather than a regular
     * [File] or the service failed to parse the [File].
     */
    fun load(file: File, charset: Charset = Charsets.UTF_8): Map<*, *>
}
