package backend.academy.game;

import backend.academy.game.GameController;
import backend.academy.game.HangmanFigure;
import backend.academy.word.Word;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameControllerTest {
    private final GameController game = new GameController(System.out, new Scanner(System.in));

    @Test
    void testDisplayGameState() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream testPrintStream = new PrintStream(outContent);

        GameController game = new GameController(testPrintStream, new Scanner(System.in));

        int leftAttempts = 5;
        char[] letters = {'_', '_', 'a', '_', 'o'};

        game.displayGameState(leftAttempts, letters);

        String expectedOutput = HangmanFigure.HANGMAN[HangmanFigure.ATTEMPTS - leftAttempts] +
            "\r\nNumber of attempts: " + leftAttempts +
            "\r\n__a_o\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void testGetUserInput() {
        String input = "a\n";
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        GameController game = new GameController(new PrintStream(outContent), new Scanner(inContent));

        String result = game.getUserInput();
        assertEquals("a", result);

        System.setIn(System.in);
    }

    @Test
    void testHandleHint() {
        Word mockWord = new Word("test", "This is a test hint");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream testPrintStream = new PrintStream(outContent);

        GameController game = new GameController(testPrintStream, new Scanner(System.in));

        boolean result = game.handleHint("hint", mockWord);

        assertTrue(result);
        assertEquals("Hint: This is a test hint\r\n", outContent.toString());
    }

    @Test
    void testIsValidInput_CorrectInput() {
        Set<Character> guessedLetters = new HashSet<>();
        assertTrue(game.isValidInput("a", guessedLetters));
    }

    @Test
    void testIsValidInput_IncorrectLength() {
        Set<Character> guessedLetters = new HashSet<>();
        assertFalse(game.isValidInput("ab", guessedLetters));
    }

    @Test
    void testIsValidInput_NonLetter() {
        Set<Character> guessedLetters = new HashSet<>();
        assertFalse(game.isValidInput("1", guessedLetters));
    }

    @Test
    void testIsValidInput_AlreadyUsed() {
        Set<Character> guessedLetters = new HashSet<>();
        guessedLetters.add('a');
        assertFalse(game.isValidInput("a", guessedLetters));
    }

    @Test
    void testProcessGuess_CorrectGuess() {
        char[] letters = {'_', '_', '_', '_'};
        String word = "test";

        boolean result = game.processGuess('t', word, letters);

        assertTrue(result);
        assertArrayEquals(new char[] {'t', '_', '_', 't'}, letters);
    }

    @Test
    void testProcessGuess_IncorrectGuess() {
        char[] letters = {'_', '_', '_', '_'};
        String word = "test";

        boolean result = game.processGuess('a', word, letters);

        assertFalse(result);
        assertArrayEquals(new char[] {'_', '_', '_', '_'}, letters);
    }

}
