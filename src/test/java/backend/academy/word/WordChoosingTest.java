package backend.academy.word;

import backend.academy.input.InputHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

class WordChoosingTest {
    private WordChoosing wordChoosing;

    @BeforeEach
    void setUp() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String simulatedInput = "easy\nanimals\n";
        ByteArrayInputStream inContent = new ByteArrayInputStream(simulatedInput.getBytes());
        Scanner scanner = new Scanner(inContent);
        InputHandler inputHandler = new InputHandler(scanner);

        Map<String, List<Word>> difficultyMap = new HashMap<>();
        difficultyMap.put("easy", List.of(new Word("elephant", "A large animal")));

        Map<String, Map<String, List<Word>>> wordList = new HashMap<>();
        wordList.put("animals", difficultyMap);

        inputHandler.wordList().putAll(wordList);

        wordChoosing = new WordChoosing(inputHandler);
    }

    @Test
    void testWordChoosingWithSimulatedInput() {
        assertEquals("animals", wordChoosing.category());
        assertEquals("easy", wordChoosing.difficulty());
        assertEquals("elephant", wordChoosing.guessWord().value());
    }

}
