package com.github.bvfnbk.templr.api.service

import com.github.bvfnbk.templr.api.model.MimeType
import java.io.File

/**
 * @author bvfnbk
 */
interface MimeService {
    /**
     * Determine the [MimeType] of the given [File].
     *
     * Please note: the [File] must exist and be a regular [File] (ie. [File.isFile] must be `true`)
     *
     * @param file The abstract [File] to get the [MimeType] of.
     * @return The [MimeType] associated to the given File
     * @throws [IllegalArgumentException] if the given [File] does not exist, is a directory rather than a regular
     * [File] or the service failed to determine the [MimeType] of the given [File].
     */
    fun getMimeType(file: File): MimeType
}
