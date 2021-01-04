package com.github.bvfnbk.templr.api.service

import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.io.InputStreamReader
import java.nio.charset.Charset

/**
 * Facilitate testing of function requiring input/output streams and readers, i.e. avoid kotlin.io extension functions
 * as their mocking is not straight forward, at least for the contained functions.
 *
 * @author bvfnbk
 */
interface IOService {
    fun createInputStreamReader(inputStream: FileInputStream, charset: Charset): InputStreamReader
    fun createInputStreamReader(source: File, charset: Charset): InputStreamReader
    fun createFileInputStream(source: File): FileInputStream
}
