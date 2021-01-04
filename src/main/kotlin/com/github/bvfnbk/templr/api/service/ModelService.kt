package com.github.bvfnbk.templr.api.service

import java.io.File

/**
 * @author bvfnbk
 */
interface ModelService {
    /**
     * Loads the model from the given abstract path.
     * @return the model as a [Map]
     * @throws [IllegalArgumentException] if the given [File] does not exist, is a directory rather than a regular
     * [File] or the service failed to parse the [File].
     */
    fun load(file: File): Map<*, *>
}
