package cc.wordview.assis.extensions

/**
 * Gets the first key ending with `suffix`
 *
 * @param suffix the part of the key to look for
 */
fun Map<String, ByteArray>.getEndingWith(suffix: String): ByteArray? {
    for (entry in this) {
        if (entry.key.endsWith(suffix))
            return entry.value
    }
    return null
}