package com.github.bvfnbk.templr

import assertk.assertThat
import com.github.bvfnbk.templr.api.TemplrApplication
import com.github.bvfnbk.templr.api.model.ApplicationArguments
import com.github.bvfnbk.templr.util.IntegrationTestCase
import com.github.bvfnbk.templr.util.hasSameContentAs
import com.github.bvfnbk.templr.util.resource.FtlResource
import com.github.bvfnbk.templr.util.resource.JsonResource
import com.github.bvfnbk.templr.util.resource.TxtResource
import org.koin.core.context.startKoin
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

/**
 * The testcase defines the required files for the _model_, the _template_ and the expected _output_. The _model_ is a
 * simple JSON object which only contains literal properties. The _template_ will just print out each defined property,
 * quoting the [String] value.
 *
 * @author bvfnbk
 */
object FlatModelIntegrationTestSpek : Spek({
    Feature("A flat JSON model") {
        Scenario("is transformed according to the given template.") {
            lateinit var testCase: IntegrationTestCase
            lateinit var application: TemplrApplication

            beforeEachGroup {
                startKoin {
                    // Reuse main application module.
                    modules(applicationModule)
                    application = koin.get()
                }
            }

            Given("All required files have been prepared.") {
                testCase = IntegrationTestCase(
                    Charsets.UTF_8,
                    JsonResource("/models/flat.json"),
                    FtlResource("/templates/flat.ftl"),
                    TxtResource("/outputs/flat.txt")
                )
            }

            When("The application is run") {
                application.run(
                    ApplicationArguments(
                        testCase.charset,
                        testCase.model,
                        testCase.template,
                        testCase.actualOutput
                    )
                )
            }

            Then("The actual output matches the expected.") {
                assertThat(testCase.actualOutput).hasSameContentAs(testCase.expectedOutput, testCase.charset)
            }

            afterEachGroup {
                testCase.cleanup()
            }
        }
    }

})
