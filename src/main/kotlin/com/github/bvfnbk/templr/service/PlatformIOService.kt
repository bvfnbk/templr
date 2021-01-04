package com.github.bvfnbk.templr.service

import com.github.bvfnbk.templr.api.service.IOService
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.nio.charset.Charset

/**
 * Utility used to improve testability (avoid usage of standard extension functions...)
 *
 * @author bvfnbk
 */
class PlatformIOService : IOService {
    override fun createInputStreamReader(inputStream: FileInputStream, charset: Charset): InputStreamReader =
        InputStreamReader(inputStream, charset)

    override fun createInputStreamReader(source: File, charset: Charset): InputStreamReader =
        createInputStreamReader(createFileInputStream(source), charset)

    override fun createFileInputStream(source: File): FileInputStream = FileInputStream(source)
}
