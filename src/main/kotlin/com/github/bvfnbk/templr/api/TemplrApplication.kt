package com.github.bvfnbk.templr.api

import com.github.bvfnbk.templr.api.model.ApplicationArguments

/**
 * @author bvfnbk
 */
interface TemplrApplication {
    fun run(arguments: ApplicationArguments)
}
