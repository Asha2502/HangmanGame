package backend.academy.input;

import backend.academy.word.Word;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

class InputHandlerTest {
    private final InputHandler inputHandler = new InputHandler(new Scanner(System.in));
    private final Map<String, Map<String, List<Word>>> testWords = inputHandler.wordList();

    @Test
    void chooseCategory() {
        Scanner scanner = new Scanner("animal\n");
        InputHandler inputHandler = new InputHandler(scanner);
        String category = inputHandler.chooseCategory();
        assertEquals("animal", category);

        scanner = new Scanner("\n");
        inputHandler = new InputHandler(scanner);
        category = inputHandler.chooseCategory();
        assertNotNull(category);
        assertTrue(testWords.containsKey(category));
    }

    @Test
    void chooseDifficulty() {
        Scanner scanner = new Scanner("easy\n");
        InputHandler inputHandler = new InputHandler(scanner);
        String difficulty = inputHandler.chooseDifficulty();
        assertEquals("easy", difficulty);

        scanner = new Scanner("\n");
        inputHandler = new InputHandler(scanner);
        difficulty = inputHandler.chooseDifficulty();

        List<String> validDifficulties = new ArrayList<>();
        for (Difficulties df : Difficulties.values()) {
            validDifficulties.add(df.toString().toLowerCase());
        }

        assertTrue(validDifficulties.contains(difficulty));
    }
}
