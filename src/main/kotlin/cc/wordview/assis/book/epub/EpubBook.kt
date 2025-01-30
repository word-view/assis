package cc.wordview.assis.book.epub

import cc.wordview.assis.extensions.getEndingWith
import cc.wordview.assis.extensions.toEncoded
import org.jsoup.Jsoup
import org.jsoup.select.Elements

/**
 * A book parsed from an epub file
 *
 * @property metadata The metadata of the epub
 * @property manifestItems Items declared in the manifest
 * @property files All the files contained in the epub archive
 *
 * @param spine The spine of the document, determining how it is structured
 */
class EpubBook(
    val metadata: Metadata,
    val manifestItems: ArrayList<ManifestItem>,
    spine: ArrayList<ItemRef>,
    val files: Map<String, ByteArray>,
) {
    val pages = ArrayList<EpubPage>()

    init {
        for (item in spine) {
            val parsedPage = parsePage(item)
            pages.add(parsedPage)
        }
    }

    private fun parsePage(spineElement: ItemRef): EpubPage {
        val manifestItem = getFromManifest(spineElement.idRef)!!
        val file = files.getEndingWith(manifestItem.href)!!.toEncoded()

        val bodyElements = ArrayList<TextualElement>()

        val html = Jsoup.parse(file)

        bodyElements.addAll(parseElements(html.body().children()))

        return EpubPage(html.title(), bodyElements)
    }

    private fun parseElements(children: Elements): ArrayList<TextualElement> {
        val bodyElements = ArrayList<TextualElement>()

        for (child in children) {
            val tagName = child.tagName()

            if (tagName == "section" || tagName == "div") {
                bodyElements.addAll(parseElements(child.children()))
            }

            val category = tagAssociations[tagName] ?: continue
            bodyElements.add(TextualElement(category, child.text()))
        }

        return bodyElements
    }

    fun getCover(): ByteArray? {
        for (item in manifestItems) {
            if (item.mediaType == "image/jpeg" || item.mediaType == "image/png" && item.href.contains("cover")) {
                return files.getEndingWith(item.href)
            }
        }
        return null
    }

    private fun getFromManifest(id: String): ManifestItem? {
        for (item in manifestItems) {
            if (item.id == id) return item
        }
        return null
    }
}