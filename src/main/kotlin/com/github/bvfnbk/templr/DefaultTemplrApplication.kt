package com.github.bvfnbk.templr

import com.github.bvfnbk.templr.api.TemplrApplication
import com.github.bvfnbk.templr.api.model.ApplicationArguments

/**
 * @author bvfnbk
 */
class DefaultTemplrApplication : TemplrApplication {
    override fun run(arguments: ApplicationArguments) {
        println("Applying templates...")
        println(arguments)
    }
}
