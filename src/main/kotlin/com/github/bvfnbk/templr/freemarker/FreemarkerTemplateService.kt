package com.github.bvfnbk.templr.freemarker

import com.github.bvfnbk.templr.api.service.TemplateService
import freemarker.cache.FileTemplateLoader
import freemarker.template.Configuration
import freemarker.template.TemplateExceptionHandler
import java.io.File
import java.io.OutputStreamWriter
import java.nio.charset.Charset

/**
 * Encapsulates Freemarker-specifics.
 *
 * @author bvfnbk
 */
class FreemarkerTemplateService : TemplateService {
    private lateinit var configuration: Configuration

    init {
        configure()
    }

    override fun apply(templateFile: File, context: Map<*, *>, writer: OutputStreamWriter) {
        val (templateDirectory, templateName) = splitTemplateFile(templateFile)

        configuration.templateLoader = FileTemplateLoader(templateDirectory)

        // Load template...
        val template = configuration.getTemplate(templateName)

        // ...process
        template.process(context, writer)
    }

    override fun configure(charset: Charset) {
        configuration = Configuration(Configuration.VERSION_2_3_30)
        configuration.templateExceptionHandler = TemplateExceptionHandler.RETHROW_HANDLER
        configuration.defaultEncoding = charset.name()
        configuration.logTemplateExceptions = false
        configuration.wrapUncheckedExceptions = false
        configuration.fallbackOnNullLoopVariable = false
    }

    /**
     * Splits given abstract path to the template [File] into its parent directory and the file name.
     *
     * @param templateFile the abstract path to the template [File]
     * @return a [Pair] containing the abstract path to the template parent directory and the name of the template.
     */
    private fun splitTemplateFile(templateFile: File): Pair<File, String> =
        Pair(templateFile.parentFile, templateFile.name)
}
