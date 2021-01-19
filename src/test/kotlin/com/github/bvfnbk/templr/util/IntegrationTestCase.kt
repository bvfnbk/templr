package com.github.bvfnbk.templr.util

import com.github.bvfnbk.templr.util.resource.Resource
import java.io.File
import java.nio.charset.Charset

/**
 * An integration test case describes all required parameters for a given integration test. It provides helpers to setup
 * the required [File]s and to clean up afterwards.
 *
 * @author bvfnbk
 */
data class IntegrationTestCase(
    val charset: Charset,
    private val modelResource: Resource,
    private val templateResource: Resource,
    private val outputResource: Resource
) {
    val model: File by lazy { modelResource.copyToTemporaryFile() }
    val template: File by lazy { templateResource.copyToTemporaryFile() }
    val expectedOutput: File by lazy { outputResource.copyToTemporaryFile() }
    val actualOutput: File by lazy { File.createTempFile("templr-resource-", outputResource.extension) }

    fun cleanup() {
        model.delete()
        template.delete()
        expectedOutput.delete()
        actualOutput.delete()
    }
}
