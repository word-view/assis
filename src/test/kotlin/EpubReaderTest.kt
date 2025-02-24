import cc.wordview.assis.parseEpub
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class EpubReaderTest {
    @Test
    fun parseDomCasmurro() {
        val book = parseEpub(File(getResource("dom_casmurro.epub")).inputStream())

        assertEquals("Public domain in the USA.", book.metadata.rights)
        assertEquals("Machado de Assis", book.metadata.creator)
        assertEquals("Dom Casmurro", book.metadata.title)
        assertEquals("pt", book.metadata.language)
        assertEquals("http://www.gutenberg.org/files/55752/55752-h/55752-h.htm", book.metadata.source)
        assertEquals("http://www.gutenberg.org/ebooks/55752", book.metadata.identifier)

        assertEquals(14, book.manifestItems.size)
        assertEquals(17, book.files.size)
        assertEquals(9, book.pages.size)

        assertEquals(237, book.pages[1].body.size)

        assertNotNull(book.getCover())
    }

    @Test
    fun parseEsauEJacob() {
        val book = parseEpub(File(getResource("esau_e_jaco.epub")).inputStream())

        assertEquals("Public domain in the USA.", book.metadata.rights)
        assertEquals("Machado de Assis", book.metadata.creator)
        assertEquals("Esau e Jacob", book.metadata.title)
        assertEquals("pt", book.metadata.language)
        assertEquals("http://www.gutenberg.org/files/56737/56737-h/56737-h.htm", book.metadata.source)
        assertEquals("http://www.gutenberg.org/ebooks/56737", book.metadata.identifier)

        assertEquals(14, book.manifestItems.size)
        assertEquals(17, book.files.size)
        assertEquals(9, book.pages.size)

        assertEquals(227, book.pages[1].body.size)

        assertNotNull(book.getCover())
    }

    @Test
    fun parseIntroducaoA() {
        val book = parseEpub(File(getResource("introducao_a.epub")).inputStream())

        assertEquals(null, book.metadata.rights)
        assertEquals("Paulo César Rodrigues", book.metadata.creator)
        assertEquals("Introdução à filosofia de Bergson", book.metadata.title)
        assertEquals("pt-BR", book.metadata.language)
        assertEquals("SciELO Books", book.metadata.source)
        assertEquals("9786557143025", book.metadata.identifier)

        assertEquals(19, book.manifestItems.size)
        assertEquals(22, book.files.size)
        assertEquals(11, book.pages.size)

        assertEquals(25, book.pages[1].body.size)

        assertNotNull(book.getCover())
    }

    @Test
    fun parseMobyDick() {
        val book = parseEpub(File(getResource("moby_dick.epub")).inputStream())

        assertEquals("Public domain in the USA.", book.metadata.rights)
        assertEquals("Herman Melville", book.metadata.creator)
        assertEquals("Moby Dick; Or, The Whale", book.metadata.title)
        assertEquals("en", book.metadata.language)
        assertEquals("https://www.gutenberg.org/files/2701/2701-h/2701-h.htm", book.metadata.source)
        assertEquals("http://www.gutenberg.org/2701", book.metadata.identifier)

        assertEquals(18, book.manifestItems.size)
        assertEquals(21, book.files.size)
        assertEquals(12, book.pages.size)

        assertEquals(253, book.pages[1].body.size)

        assertNotNull(book.getCover())
    }

    @Test
    fun parseLusiadas() {
        val book = parseEpub(File(getResource("lusiadas.epub")).inputStream())

        assertEquals("Public domain in the USA.", book.metadata.rights)
        assertEquals("Luís de Camões", book.metadata.creator)
        assertEquals("Os Lusíadas", book.metadata.title)
        assertEquals("pt", book.metadata.language)
        assertEquals("https://www.gutenberg.org/files/27236/27236-0.txt", book.metadata.source)
        assertEquals("http://www.gutenberg.org/27236", book.metadata.identifier)

        assertEquals(10, book.manifestItems.size)
        assertEquals(13, book.files.size)
        assertEquals(4, book.pages.size)

        assertEquals(609, book.pages[1].body.size)

        assertNotNull(book.getCover())
    }
}