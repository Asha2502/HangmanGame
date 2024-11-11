package backend.academy.word;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordReader {
    private static final String FILE_PATH = "words.csv";
    private static final Map<String, Map<String, List<Word>>> WORD_LIST = new HashMap<>();
    private static final int DIFFICULTY_POSITION = 0;
    private static final int CATEGORY_POSITION = 1;
    private static final int VALUE_POSITION = 2;
    private static final int HINT_POSITION = 3;
    private static final int NUMBER_OF_WORDS_IN_LINE = 4;

    public Map<String, Map<String, List<Word>>> loadWordsFromFile() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(FILE_PATH);
        if (inputStream == null) {
            throw new FileNotFoundException("File wasn't found: " + FILE_PATH);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == NUMBER_OF_WORDS_IN_LINE) {
                    String difficulty = parts[DIFFICULTY_POSITION].trim();
                    String category = parts[CATEGORY_POSITION].trim();
                    String value = parts[VALUE_POSITION].trim();
                    String hint = parts[HINT_POSITION].trim();
                    Word word = new Word(value, hint);

                    WORD_LIST.computeIfAbsent(category, k -> new HashMap<>());
                    WORD_LIST.get(category).computeIfAbsent(difficulty, k -> new ArrayList<>());
                    WORD_LIST.get(category).get(difficulty).add(word);
                }
            }
        }
        return WORD_LIST;
    }
}
