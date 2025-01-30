package cc.wordview.assis.book.epub

class ManifestItem(val href: String, val mediaType: String, val id: String) {
    override fun toString(): String {
        return "ManifestItem(href='$href', mediaType='$mediaType', id='$id')"
    }
}