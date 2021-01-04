package com.github.bvfnbk.templr.api

import com.github.bvfnbk.templr.api.model.ApplicationArguments
import java.nio.charset.Charset

/**
 * @author bvfnbk
 */
interface TemplrApplication {
    var charset: Charset

    fun run(arguments: ApplicationArguments)
}
