package com.github.bvfnbk.templr.service

import assertk.assertThat
import assertk.assertions.hasMessage
import assertk.assertions.isEqualTo
import assertk.assertions.isFailure
import assertk.assertions.isInstanceOf
import com.github.bvfnbk.templr.api.model.MimeType
import io.mockk.every
import io.mockk.mockk
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.io.File

/**
 * @author bvfnbk
 */
object ExtensionBasedMimeServiceSpek : Spek({
    val service = ExtensionBasedMimeService()
    describe("The MIME detection service...") {
        describe("...is case insensitive:") {
            it("It detectes all lower case...") {
                assertThat(service.getMimeType("test.json")).isEqualTo(MimeType.JSON)
            }
            it("It detects all upper case...") {
                assertThat(service.getMimeType("test.JSON")).isEqualTo(MimeType.JSON)
            }
            it("It detects mixed case...") {
                assertThat(service.getMimeType("test.jSoN")).isEqualTo(MimeType.JSON)
            }
        }

        describe("...throws an error, if...") {
            it("...given file does not provide a extension...") {
                assertThat {
                    service.getMimeType("filename")
                }.isFailure().isInstanceOf(IllegalArgumentException::class).hasMessage("Unknown file extension: ''.")
            }

            it("...given file provides an unknown extension...") {
                val unknownExtension = "dfjkd"
                assertThat {
                    service.getMimeType("filename.$unknownExtension")
                }.isFailure().isInstanceOf(IllegalArgumentException::class)
                    .hasMessage("Unknown file extension: '$unknownExtension'.")
            }

            it("...given abstract File is not a regular file...") {
                // Please note: this test also covers the non-existence.
                val directory = mockk<File>()
                every {
                    directory.isFile
                }.returns(false)

                every {
                    directory.name
                }.returns("directory.json")

                assertThat {
                    service.getMimeType(directory)
                }.isFailure().isInstanceOf(java.lang.IllegalArgumentException::class)
                    .hasMessage("Given abstract file is no regular file.")
            }
        }
    }
})

