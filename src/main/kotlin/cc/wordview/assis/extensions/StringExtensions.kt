package cc.wordview.assis.extensions

import org.xml.sax.InputSource
import java.io.ByteArrayInputStream

fun String.toInputSource(): InputSource {
    return InputSource(ByteArrayInputStream(this.encodeToByteArray()))
}