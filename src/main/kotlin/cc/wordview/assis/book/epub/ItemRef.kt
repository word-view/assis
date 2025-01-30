package cc.wordview.assis.book.epub

class ItemRef(val idRef: String, val linear: String) {
    override fun toString(): String {
        return "ItemRef(idRef='$idRef', linear='$linear')"
    }
}