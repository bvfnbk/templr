package com.github.bvfnbk.templr.service

import com.github.bvfnbk.templr.api.service.TemplateService
import freemarker.template.Configuration
import java.io.File

/**
 * @author bvfnbk
 */
class FreemarkerTemplateService(private val configuration: Configuration) : TemplateService {
    override fun apply(charset: String, template: File, model: File, output: File) {
        TODO("Not yet implemented")
    }
}
