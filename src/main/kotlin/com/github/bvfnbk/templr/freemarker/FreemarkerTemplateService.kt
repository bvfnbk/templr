package com.github.bvfnbk.templr.freemarker

import com.github.bvfnbk.templr.api.service.TemplateService
import freemarker.cache.FileTemplateLoader
import freemarker.template.Configuration
import java.io.File
import java.io.OutputStreamWriter

/**
 * Encapsulates Freemarker-specifics.
 *
 * @author bvfnbk
 */
class FreemarkerTemplateService(private val configuration: Configuration) : TemplateService {
    override fun apply(templateFile: File, context: Map<*, *>, writer: OutputStreamWriter) {
        val (templateDirectory, templateName) = splitTemplateFile(templateFile)

        configuration.templateLoader = FileTemplateLoader(templateDirectory)

        // Load template...
        val template = configuration.getTemplate(templateName)

        // ...process
        template.process(context, writer)
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
