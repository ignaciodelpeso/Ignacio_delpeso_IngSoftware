package demo2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SearcherTestCase {

    private Searcher searcher;
    private List<String> words;

    @BeforeEach
    void setUp() {
        searcher = new Searcher();
        words = Arrays.asList("apple", "application", "banana", "grape", "pineapple");
    }

    // ---------- searchWord ----------

    @Test
    @DisplayName("searchWord: la palabra existe en la lista")
    void searchWord_existingWord_returnsTrue() {
        assertTrue(searcher.searchWord("banana", words));
    }

    @Test
    @DisplayName("searchWord: la palabra no existe en la lista")
    void searchWord_missingWord_returnsFalse() {
        assertFalse(searcher.searchWord("orange", words));
    }

    @Test
    @DisplayName("searchWord: lista vacía")
    void searchWord_emptyList_returnsFalse() {
        assertFalse(searcher.searchWord("banana", Collections.emptyList()));
    }

    // ---------- getWordByIndex ----------

    @Test
    @DisplayName("getWordByIndex: índice válido devuelve la palabra correcta")
    void getWordByIndex_validIndex_returnsWord() {
        assertEquals("apple", searcher.getWordByIndex(words, 0));
        assertEquals("banana", searcher.getWordByIndex(words, 2));
        assertEquals("pineapple", searcher.getWordByIndex(words, words.size() - 1));
    }

    @Test
    @DisplayName("getWordByIndex: índice negativo devuelve null")
    void getWordByIndex_negativeIndex_returnsNull() {
        assertNull(searcher.getWordByIndex(words, -1));
    }

    @Test
    @DisplayName("getWordByIndex: índice demasiado grande devuelve null")
    void getWordByIndex_indexTooBig_returnsNull() {
        assertNull(searcher.getWordByIndex(words, words.size()));
        assertNull(searcher.getWordByIndex(words, 100));
    }

    @Test
    @DisplayName("getWordByIndex: lista vacía devuelve null")
    void getWordByIndex_emptyList_returnsNull() {
        assertNull(searcher.getWordByIndex(Collections.emptyList(), 0));
    }

    // ---------- searchByPrefix ----------

    @Test
    @DisplayName("searchByPrefix: devuelve las palabras que empiezan con el prefijo")
    void searchByPrefix_returnsWordsWithPrefix() {
        List<String> result = searcher.searchByPrefix("app", words);

        assertEquals(2, result.size());
        assertTrue(result.contains("apple"));
        assertTrue(result.contains("application"));
    }

    @Test
    @DisplayName("searchByPrefix: no incluye palabras que no empiezan con el prefijo")
    void searchByPrefix_excludesWordsWithoutPrefix() {
        List<String> result = searcher.searchByPrefix("app", words);

        // "pineapple" contiene "app" pero no empieza por "app"
        assertFalse(result.contains("pineapple"));
        assertFalse(result.contains("banana"));
        assertFalse(result.contains("grape"));
    }

    @Test
    @DisplayName("searchByPrefix: ninguna coincidencia devuelve lista vacía")
    void searchByPrefix_noMatches_returnsEmptyList() {
        assertTrue(searcher.searchByPrefix("xyz", words).isEmpty());
    }

    // ---------- filterByKeyword ----------

    @Test
    @DisplayName("filterByKeyword: devuelve todos los elementos que contienen la palabra clave")
    void filterByKeyword_returnsAllElementsContainingKeyword() {
        List<String> result = searcher.filterByKeyword("app", words);

        assertEquals(Arrays.asList("apple", "application", "pineapple"), result);
    }

    @Test
    @DisplayName("filterByKeyword: no devuelve ninguno si la palabra clave no existe")
    void filterByKeyword_keywordNotFound_returnsEmptyList() {
        assertTrue(searcher.filterByKeyword("xyz", words).isEmpty());
    }

    // ---------- searchExactPhrase (avanzado) ----------

    @Test
    @DisplayName("searchExactPhrase: la frase es el primer elemento")
    void searchExactPhrase_firstElement_returnsTrue() {
        assertTrue(searcher.searchExactPhrase("apple", words));
    }

    @Test
    @DisplayName("searchExactPhrase: la frase NO es el primer elemento (fallaba antes de corregir)")
    void searchExactPhrase_notFirstElement_returnsTrue() {
        assertTrue(searcher.searchExactPhrase("banana", words));
        assertTrue(searcher.searchExactPhrase("pineapple", words));
    }

    @Test
    @DisplayName("searchExactPhrase: la frase no existe en la lista")
    void searchExactPhrase_missingPhrase_returnsFalse() {
        assertFalse(searcher.searchExactPhrase("orange", words));
    }

    @Test
    @DisplayName("searchExactPhrase: coincidencia parcial no cuenta como exacta")
    void searchExactPhrase_partialMatch_returnsFalse() {
        assertFalse(searcher.searchExactPhrase("app", words));
    }

    @Test
    @DisplayName("searchExactPhrase: lista vacía")
    void searchExactPhrase_emptyList_returnsFalse() {
        assertFalse(searcher.searchExactPhrase("apple", Collections.emptyList()));
    }
}
