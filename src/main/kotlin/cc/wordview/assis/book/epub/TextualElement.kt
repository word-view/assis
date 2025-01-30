package cc.wordview.assis.book.epub

/**
 * Determines the structure of the body of a page
 *
 * @property category Type of element
 * @property value The value of the element
 */
@Suppress("MemberVisibilityCanBePrivate")
class TextualElement(val category: ElementCategory, val value: String) {
    override fun toString(): String {
        return "TextualElement(category=$category, value='$value')"
    }
}

val tagAssociations = hashMapOf(
    "p" to ElementCategory.PARAGRAPH,
    "h1" to ElementCategory.HEADER1,
    "h2" to ElementCategory.HEADER2,
    "h3" to ElementCategory.HEADER3,
    "hr" to ElementCategory.HORIZONTAL_RULE,
)

enum class ElementCategory {
    PARAGRAPH,
    HEADER1,
    HEADER2,
    HEADER3,

    /**
     * Represents the `<hr/>` HTML tag
     */
    HORIZONTAL_RULE
}