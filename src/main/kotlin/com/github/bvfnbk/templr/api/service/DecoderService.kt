package com.github.bvfnbk.templr.api.service

import com.github.bvfnbk.templr.api.model.Model
import java.io.InputStreamReader

/**
 * @author bvfnbk
 */
interface DecoderService {
    fun decode(reader: InputStreamReader): Model<*>
}
