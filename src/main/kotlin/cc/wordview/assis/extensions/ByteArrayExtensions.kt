package cc.wordview.assis.extensions

import java.nio.charset.Charset

fun ByteArray.toEncoded(): String {
    return this.toString(Charset.defaultCharset()).trim()
}