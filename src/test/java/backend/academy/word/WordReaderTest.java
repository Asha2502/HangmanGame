package backend.academy.word;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class WordReaderTest {

    private final WordReader wordReader = new WordReader();

    @Test
    void loadWordsFromFile() throws IOException {
        Map<String, Map<String, List<Word>>> testWords = wordReader.loadWordsFromFile();

        assertNotNull(testWords);
        assertFalse(testWords.isEmpty());
        assertTrue(testWords.containsKey("animal"));
        assertTrue(testWords.get("animal").containsKey("easy"));
        List<Word> easyAnimalWords = testWords.get("animal").get("easy");
        assertFalse(easyAnimalWords.isEmpty());
    }
}
