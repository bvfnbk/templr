package com.github.bvfnbk.templr.service

import com.github.bvfnbk.templr.api.model.MimeType
import com.github.bvfnbk.templr.api.service.MimeService
import java.io.File

/**
 * The [ExtensionBasedMimeService] tries to determine the [MimeType] of a given file based upon its file name extension.
 *
 * @author bvfnbk
 */
class ExtensionBasedMimeService : MimeService {
    override fun getMimeType(file: File): MimeType {
        if (!file.isFile) {
            throw IllegalArgumentException("Given abstract file is no regular file.")
        }
        return getMimeType(file.name)
    }

    /**
     * Tries to detect the MimeType of an abstract [File] for the given file name. This method skips the check whether
     * the corresponding [File] exists or is a regular [File].
     *
     * @param fileName the name of the [File] for which the [MimeType] is required.
     * @return the [MimeType] associated to the given [File] name.
     * @throws [IllegalArgumentException] if the given file name has no extension or the extension is not known.
     */
    fun getMimeType(fileName: String): MimeType {
        val extension = File(fileName).extension
        return MimeType.values().firstOrNull { mimeType ->
            mimeType.extension == extension.toLowerCase()
        } ?: throw IllegalArgumentException("Unknown file extension: '$extension'.")
    }
}
