package com.github.bvfnbk.templr.api.service

import java.io.InputStreamReader

/**
 * @author bvfnbk
 */
interface DecoderService {
    fun decode(reader: InputStreamReader): Map<String, Any>
}
