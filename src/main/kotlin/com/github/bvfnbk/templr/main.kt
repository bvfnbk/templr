package com.github.bvfnbk.templr

import com.github.bvfnbk.templr.api.TemplrApplication
import com.github.bvfnbk.templr.api.service.*
import com.github.bvfnbk.templr.freemarker.FreemarkerTemplateService
import com.github.bvfnbk.templr.service.DefaultIOService
import com.github.bvfnbk.templr.service.DefaultModelService
import com.github.bvfnbk.templr.service.ExtensionBasedMimeService
import com.github.bvfnbk.templr.service.KotlinxSerializationDecoderService
import org.koin.core.context.startKoin
import org.koin.dsl.module


val applicationModule = module {
    // Freemarker Template Service:
    single<TemplateService> { FreemarkerTemplateService() }

    // Services
    single<IOService> { DefaultIOService() }
    single<MimeService> { ExtensionBasedMimeService() }
    single<DecoderService> { KotlinxSerializationDecoderService() }
    single<ModelService> { DefaultModelService(get(), get(), get()) }

    // Application:
    single<TemplrApplication> { DefaultTemplrApplication(get(), get(), get()) }

    // Clikt
    single { TemplrCommand(get()) }
}


fun main(args: Array<String>) {
    startKoin {
        modules(applicationModule)

        val command = koin.get<TemplrCommand>()
        command.main(args)
    }
}
