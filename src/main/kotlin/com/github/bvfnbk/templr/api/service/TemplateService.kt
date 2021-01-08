package com.github.bvfnbk.templr.api.service

import java.io.File
import java.io.OutputStreamWriter
import java.nio.charset.Charset

/**
 * @author bvfnbk
 */
interface TemplateService {
    /**
     * Configures the template service to use the given charset.
     *
     * @param charset The [Charset] to be used; default: [Charsets.UTF_8]
     */
    fun configure(charset: Charset = Charsets.UTF_8)

    /**
     * Combine the given template and model [File] and create a new output [File].
     *
     * @param templateFile The abstract path to the template [File].
     * @param context The [Map] defining the context.
     * @param writer The [OutputStreamWriter] where the result is written to.
     */
    fun apply(templateFile: File, context: Map<*, *>, writer: OutputStreamWriter)
}
