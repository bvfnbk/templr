# `templr`

[![Build Status](https://travis-ci.com/bvfnbk/templr.svg?branch=main)](https://travis-ci.com/bvfnbk/templr)
[![CodeFactor](https://www.codefactor.io/repository/github/bvfnbk/templr/badge)](https://www.codefactor.io/repository/github/bvfnbk/templr)

---

A command-line client to combine JSON _models_ with _templates_ in order to create files. It embeds the _Freemarker_
template engine and was a result of a quick _Kotlin_ script hack.

## Usage

```bash
Usage: templr [OPTIONS] OUTPUT

  Runs the templr application.

Options:
  -c, --charset TEXT   The input/output charset to be used.
  -m, --model PATH     The path to the JSON model.
  -t, --template PATH  The path to the freemarker template.
  -h, --help           Show this message and exit
```

Load the _model_ from a JSON file `./model.json` and use the _Freemarker_ template `./template.ftl` to generate
the `./output` file:

```bash
templr --model ./model.json --template ./template.ftl ./output
```

**Please note:**

* The model file must exist and be a regular file.
* The template file must exist and be a regular file.
* The output file may exist and must be a regular file in that case.

## Resources

* The embedded template engine: https://freemarker.apache.org/
* Unintentionally borrowing some ideas from https://github.com/sgoeschl/freemarker-cli
