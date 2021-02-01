package com.github.bvfnbk.templr.service

import com.github.bvfnbk.templr.api.model.Model
import com.github.bvfnbk.templr.api.model.MimeType
import com.github.bvfnbk.templr.api.service.DecoderService
import com.github.bvfnbk.templr.api.service.IOService
import com.github.bvfnbk.templr.api.service.MimeService
import com.github.bvfnbk.templr.api.service.ModelService
import java.io.File
import java.nio.charset.Charset

/**
 * @author bvfnbk
 */
class DefaultModelService(
    private val mimeService: MimeService,
    private val decoderService: DecoderService,
    private val ioService: IOService
) : ModelService {
    override fun load(file: File, charset: Charset): Model<*> = when (mimeService.getMimeType(file)) {
        MimeType.JSON -> loadJson(file, charset)
    }

    private fun loadJson(file: File, charset: Charset): Model<*> {
        return decoderService.decode(ioService.createInputStreamReader(file, charset))
    }
}
