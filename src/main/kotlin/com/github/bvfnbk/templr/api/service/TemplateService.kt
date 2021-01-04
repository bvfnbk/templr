package com.github.bvfnbk.templr.api.service

import java.io.File
import java.io.OutputStreamWriter

/**
 * @author bvfnbk
 */
interface TemplateService {
    /**
     * Combine the given template and model [File] and create a new output [File].
     *
     * @param templateFile The abstract path to the template [File].
     * @param context The [Map] defining the context.
     * @param writer The [OutputStreamWriter] where the result is written to.
     */
    fun apply(templateFile: File, context: Map<*, *>, writer: OutputStreamWriter)
}
