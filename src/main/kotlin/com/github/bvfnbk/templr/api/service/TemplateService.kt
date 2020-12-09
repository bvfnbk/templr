package com.github.bvfnbk.templr.api.service

import java.io.File

/**
 * @author bvfnbk
 */
interface TemplateService {
    /**
     * Combine the given template and model File and create a new output File.
     */
    fun apply(charset: String, template: File, model: File, output: File)
}
