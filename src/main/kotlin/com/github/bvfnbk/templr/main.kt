package com.github.bvfnbk.templr

import com.github.ajalt.clikt.completion.CompletionCandidates
import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.options.convert
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import com.github.ajalt.clikt.parameters.types.file
import com.github.bvfnbk.templr.api.TemplrApplication
import com.github.bvfnbk.templr.api.model.ApplicationArguments
import java.nio.charset.Charset


class TemplrCommand(private var application: TemplrApplication) : CliktCommand(help = "Runs the templr application.") {
    private val charset by option(
        "-c",
        "--charset",
        help = "The input/output charset to be used.",
        metavar = "CHARSET",
        completionCandidates = CompletionCandidates.Fixed(Charset.availableCharsets().keys)
    ).convert {
        Charset.forName(it)
    }.default(Charsets.UTF_8, defaultForHelp = "UTF-8")

    private val model by option("-m", "--model", help = "The path to the JSON model.")
        .file()
        .required()

    private val template by option("-t", "--template", help = "The path to the freemarker template.")
        .file()
        .required()

    private val output by argument().file()

    override fun run() {
        application.charset = charset
        application.run(ApplicationArguments(model, template, output))
    }
}

fun main(args: Array<String>) {
    TemplrCommand(DefaultTemplrApplication()).main(args)
}
