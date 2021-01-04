package com.github.bvfnbk.templr

import com.github.bvfnbk.templr.api.TemplrApplication
import com.github.bvfnbk.templr.api.model.ApplicationArguments
import com.github.bvfnbk.templr.api.service.*
import com.github.bvfnbk.templr.freemarker.FreemarkerTemplateService
import com.github.bvfnbk.templr.service.DefaultModelService
import com.github.bvfnbk.templr.service.ExtensionBasedMimeService
import com.github.bvfnbk.templr.service.KotlinxSerializationDecoderService
import com.github.bvfnbk.templr.service.PlatformIOService
import freemarker.template.Configuration
import freemarker.template.TemplateExceptionHandler
import java.nio.charset.Charset

/**
 * Dirty main. Configures all dependencies and runs the template service.
 *
 * Create delegate the run to an application which receives its dependencies via constructor?
 * ...or is this another indirection, delaying the test another step back...
 *
 * @author bvfnbk
 */
class DefaultTemplrApplication : TemplrApplication {
    override var charset: Charset = Charsets.UTF_8

    private val configuration: Configuration by lazy {
        val configuration = Configuration(Configuration.VERSION_2_3_30)

        configuration.templateExceptionHandler = TemplateExceptionHandler.RETHROW_HANDLER
        configuration.defaultEncoding = charset.name()
        configuration.logTemplateExceptions = false
        configuration.wrapUncheckedExceptions = false
        configuration.fallbackOnNullLoopVariable = false

        configuration
    }

    private val decoderService: DecoderService by lazy { KotlinxSerializationDecoderService() }

    private val ioService: IOService by lazy { PlatformIOService() }

    private val mimeService: MimeService by lazy { ExtensionBasedMimeService() }

    private val modelService: ModelService by lazy {
        DefaultModelService(charset, mimeService, decoderService, ioService)
    }

    private val templateService: TemplateService by lazy {
        FreemarkerTemplateService(configuration)
    }

    override fun run(arguments: ApplicationArguments) {
        val context = modelService.load(arguments.model)
        arguments.output.writer(charset).use { writer ->
            templateService.apply(arguments.template, context, writer)
        }
    }
}
