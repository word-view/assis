package cc.wordview.assis.extensions

import org.w3c.dom.Document
import org.w3c.dom.Element

fun Document.getFirstElementWithTagName(tag: String): Element? {
    return this.getElementsByTagName(tag).item(0) as? Element
}