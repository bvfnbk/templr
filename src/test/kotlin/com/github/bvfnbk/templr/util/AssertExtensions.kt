package com.github.bvfnbk.templr.util

import assertk.Assert
import assertk.assertions.support.expected
import java.io.File
import java.nio.charset.Charset

/**
 * @author bvfnbk
 */

fun Assert<File>.hasSameContentAs(expected: File, charset: Charset = Charsets.UTF_8) = given { actual ->
    val actualContents = actual.readText(charset)
    val expectedContents = expected.readText(charset)

    if (actualContents == expectedContents) {
        return
    }
    expected("Expected output:\n\n${expectedContents}\nbut contains\n\n${actualContents}")
}
