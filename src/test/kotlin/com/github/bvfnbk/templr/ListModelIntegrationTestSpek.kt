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
import org.koin.core.context.stopKoin
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

/**
 * The testcase defines the required files for the _model_, the _template_ and the expected _output_. The _model_ is a
 * simple JSON _list_ which contains relatively simple objects. The _template_ will just print out each object in a row.
 *
 * @author bvfnbk
 */
object ListModelIntegrationTestSpek : Spek({
    Feature("A simple JSON list model") {
        Scenario("is transformed according to the given template.") {
            lateinit var testCase: IntegrationTestCase
            lateinit var application: TemplrApplication

            beforeEachGroup {
                startKoin {
                    modules(applicationModule)
                    application = koin.get()
                }
            }

            Given("All required files have been prepared.") {
                testCase = IntegrationTestCase(
                    Charsets.UTF_8,
                    JsonResource("/models/list.json"),
                    FtlResource("/templates/list.ftl"),
                    TxtResource("/outputs/list.txt")
                )
            }

            When("The application is run") {
                application.run(
                    ApplicationArguments(
                        testCase.charset,
                        testCase.model,
                        testCase.template,
                        testCase.actualOutput,
                        emptyMap()
                    )
                )
            }

            Then("The actual output matches the expected.") {
                assertThat(testCase.actualOutput).hasSameContentAs(testCase.expectedOutput, testCase.charset)
            }

            afterEachGroup {
                testCase.cleanup()
                stopKoin()
            }
        }
    }

})
