package cc.wordview.assis

import cc.wordview.assis.book.epub.EpubBook
import cc.wordview.assis.book.epub.Metadata
import cc.wordview.assis.book.epub.ItemRef
import cc.wordview.assis.book.epub.ManifestItem
import cc.wordview.assis.exception.ParseException
import cc.wordview.assis.extensions.getFirstElementWithTagName
import cc.wordview.assis.extensions.toEncoded
import cc.wordview.assis.extensions.toInputSource
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import java.io.InputStream
import java.util.zip.ZipInputStream
import javax.xml.parsers.DocumentBuilderFactory

private val documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder()

/**
 * Parses an Epub file
 *
 * @param inputStream input stream of the epub file
 * @return The parsed epub object
 */
fun parseEpub(inputStream: InputStream): EpubBook {
    val zipArchive = ZipInputStream(inputStream)
    var entry = zipArchive.nextEntry

    val files = HashMap<String, ByteArray>()

    while (entry != null) {
        if (!entry.isDirectory) {
            files[entry.name] = zipArchive.readAllBytes()
        }
        entry = zipArchive.nextEntry
    }

    val rootFilePath = getRootFile(files["META-INF/container.xml"]!!.toEncoded())
    val rootFile = files[rootFilePath]!!.toEncoded().toInputSource()

    val document = documentBuilder.parse(rootFile)!!
    document.documentElement.normalize()

    return EpubBook(
        getMetadata(document),
        getManifestItems(document),
        getSpine(document),
        files
    )
}

private fun getRootFile(container: String): String {
    val document = documentBuilder.parse(container.toInputSource())!!
    document.documentElement.normalize()

    val nodeList = document.getFirstElementWithTagName("rootfile")!!
    val rootFilePath = nodeList.getAttribute("full-path")

    return rootFilePath ?: throw ParseException("Failed to find root file")
}


private fun getMetadata(document: Document): Metadata {
    return Metadata(
        document.getFirstElementWithTagName("dc:rights")?.textContent,
        document.getFirstElementWithTagName("dc:identifier")?.textContent,
        document.getFirstElementWithTagName("dc:creator")?.textContent,
        document.getFirstElementWithTagName("dc:title")?.textContent,
        document.getFirstElementWithTagName("dc:language")?.textContent,
        document.getFirstElementWithTagName("dc:source")?.textContent
    )
}

private fun getManifestItems(document: Document): ArrayList<ManifestItem> {
    val nodeList = document.getElementsByTagName("item")

    val items = ArrayList<ManifestItem>()

    for (i in 0 until nodeList.length) {
        val node = nodeList.item(i)
        if (node.nodeType == Node.ELEMENT_NODE) {
            val elem = node as Element

            val href = elem.getAttribute("href")
            val mediaType = elem.getAttribute("media-type")
            val id = elem.getAttribute("id")

            items.add(ManifestItem(href, mediaType, id))
        }
    }

    return items
}

private fun getSpine(document: Document): ArrayList<ItemRef> {
    val nodeList = document.getElementsByTagName("itemref")

    val items = ArrayList<ItemRef>()

    for (i in 0 until nodeList.length) {
        val node = nodeList.item(i)
        if (node.nodeType == Node.ELEMENT_NODE) {
            val elem = node as Element

            val idref = elem.getAttribute("idref")
            val linear = elem.getAttribute("linear")

            items.add(ItemRef(idref, linear))
        }
    }

    return items
}