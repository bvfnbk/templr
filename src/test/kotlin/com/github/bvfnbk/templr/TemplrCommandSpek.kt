package com.github.bvfnbk.templr

import assertk.assertThat
import assertk.assertions.isFailure
import com.github.bvfnbk.templr.api.TemplrApplication
import com.github.bvfnbk.templr.api.model.ApplicationArguments
import io.mockk.mockk
import io.mockk.verify
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.io.File

/**
 * @author bvfnbk
 */
object TemplrCommandSpek : Spek({
    describe("Required parameters are validated.") {
        it("The --model parameter is required.") {
            // Given
            val args = arrayOf("--template", "template.ftl", "output")
            val app = mockk<TemplrApplication>(relaxUnitFun = true)
            val command = TemplrCommand(app)

            // When/Then
            assertThat {
                command.parse(args)
            }.isFailure()
        }

        it("The --template parameter is required.") {
            // Given
            val args = arrayOf("--model", "model.json", "output")
            val app = mockk<TemplrApplication>(relaxUnitFun = true)
            val command = TemplrCommand(app)

            // When/Then
            assertThat {
                command.parse(args)
            }.isFailure()
        }

        it("The output argument is required.") {
            // Given
            val args = arrayOf("--model", "model.json", "--template", "template.ftl")
            val app = mockk<TemplrApplication>(relaxUnitFun = true)
            val command = TemplrCommand(app)

            // When/Then
            assertThat {
                command.parse(args)
            }.isFailure()
        }
    }

    describe("The charset can be configured") {
        it("The default charset is used if nothing specified") {
            // Given
            val args = arrayOf("--model", "model.json", "--template", "template.ftl", "output")
            val app = mockk<TemplrApplication>(relaxUnitFun = true)
            val command = TemplrCommand(app)

            // When
            command.parse(args)

            // Then
            verify {
                app.run(
                    ApplicationArguments(
                        "UTF-8",
                        File("model.json"),
                        File("template.ftl"),
                        File("output")
                    )
                )
            }
        }

        describe("The specified charset is being used.") {
            val expected = "ISO-8859-1"
            val commonArguments = arrayOf("--model", "model.json", "--template", "template.ftl", "output")
            val expectedArguments = ApplicationArguments(
                expected,
                File("model.json"),
                File("template.ftl"),
                File("output")
            )

            it("Using short option") {
                // Given
                val args = commonArguments + arrayOf("-c", expected)
                val app = mockk<TemplrApplication>(relaxUnitFun = true)
                val command = TemplrCommand(app)

                // When
                command.parse(args)

                // Then
                verify {
                    app.run(expectedArguments)
                }
            }

            it("Using long option") {
                // Given
                val args = commonArguments + arrayOf("--charset", expected)
                val app = mockk<TemplrApplication>(relaxUnitFun = true)
                val command = TemplrCommand(app)

                // When
                command.parse(args)

                // Then
                verify {
                    app.run(expectedArguments)
                }
            }
        }
    }
})
