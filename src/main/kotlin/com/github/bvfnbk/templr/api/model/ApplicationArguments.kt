package com.github.bvfnbk.templr.api.model

import java.io.File

/**
 * The application arguments encapsulate the arguments received from the commandline.
 *
 * @author bvfnbk
 */
data class ApplicationArguments(
    val model: File,
    val template: File,
    val output: File
)
