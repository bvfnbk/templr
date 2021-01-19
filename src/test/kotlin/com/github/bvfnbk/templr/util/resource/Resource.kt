package com.github.bvfnbk.templr.util.resource

import java.io.File
import java.io.FileOutputStream

/**
 * @author bvfnbk
 */
open class Resource(private val path: String, val extension: String) {
    /**
     * Copies the given resource to a new temporary file.
     *
     * @return A temporary [File] corresponding to the resource.
     */
    fun copyToTemporaryFile(): File {
        val result = File.createTempFile("templr-resource-", extension)
        Resource::class.java.getResourceAsStream(path).use { source ->
            val output = FileOutputStream(result)
            output.use { target ->
                source.copyTo(target)
            }
        }

        return result
    }
}
