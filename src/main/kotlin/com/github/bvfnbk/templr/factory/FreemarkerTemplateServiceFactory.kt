package com.github.bvfnbk.templr.factory

import com.github.bvfnbk.templr.api.service.TemplateService
import com.github.bvfnbk.templr.service.FreemarkerTemplateService
import freemarker.template.Configuration
import freemarker.template.TemplateExceptionHandler
import java.io.File

/**
 * @author bvfnbk
 */
class FreemarkerTemplateServiceFactory {
    fun create(templateDirectory: File, charset: String): TemplateService {
        val configuration = Configuration(Configuration.VERSION_2_3_30)
        
        configuration.setDirectoryForTemplateLoading(templateDirectory)
        configuration.defaultEncoding = charset
        configuration.templateExceptionHandler = TemplateExceptionHandler.RETHROW_HANDLER
        configuration.logTemplateExceptions = false
        configuration.wrapUncheckedExceptions = false
        configuration.fallbackOnNullLoopVariable = false

        return FreemarkerTemplateService(configuration)
    }
}
