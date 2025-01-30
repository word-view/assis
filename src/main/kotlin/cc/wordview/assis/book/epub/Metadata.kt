package cc.wordview.assis.book.epub

class Metadata(
    val rights: String?,
    val identifier: String?,
    val creator: String?,
    val title: String?,
    val language: String?,
    val source: String?,
) {
    override fun toString(): String {
        return "BookMetadata(rights=$rights, identifier=$identifier, creator=$creator, title=$title, language=$language, source=$source)"
    }
}