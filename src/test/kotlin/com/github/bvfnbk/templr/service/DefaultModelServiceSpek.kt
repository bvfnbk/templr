package com.github.bvfnbk.templr.service

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.github.bvfnbk.templr.api.model.MimeType
import com.github.bvfnbk.templr.api.service.DecoderService
import com.github.bvfnbk.templr.api.service.IOService
import com.github.bvfnbk.templr.api.service.MimeService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.io.File
import java.io.InputStreamReader

/**
 * @author bvfnbk
 */
object DefaultModelServiceSpek : Spek({
    describe("The model service...") {
        it("...is able to load JSON files.") {
            // Given
            val source = mockk<File>()

            // internals:
            val reader = mockk<InputStreamReader>()

            // services
            val ioService = mockk<IOService>()
            val decoderService = mockk<DecoderService>()
            val mimeService = mockk<MimeService>()

            // result:
            val result = mapOf("key" to "value")

            // mock behaviour
            every {
                mimeService.getMimeType(any())
            }.returns(MimeType.JSON)

            every {
                ioService.createInputStreamReader(any<File>())
            }.returns(reader)
            every {
                decoderService.decode(any())
            }.returns(result)

            // system under test:
            val service = DefaultModelService(mimeService, decoderService, ioService)

            // When
            val actual = service.load(source)

            // Then
            verify {
                mimeService.getMimeType(eq(source))
                ioService.createInputStreamReader(eq(source))
                decoderService.decode(eq(reader))
            }
            assertThat(actual).isEqualTo(result)
        }
    }
})
