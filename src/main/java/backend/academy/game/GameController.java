package backend.academy.game;

import backend.academy.word.Word;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class GameController {
    private final PrintStream printStream;
    private final Scanner scanner;

    public GameController(PrintStream printStream, Scanner scanner) {
        this.printStream = printStream;
        this.scanner = scanner;
    }

    public void displayGameState(int leftAttempts, char[] letters) {
        printStream.println(HangmanFigure.HANGMAN[HangmanFigure.ATTEMPTS - leftAttempts]);
        printStream.println("Number of attempts: " + leftAttempts);

        for (char ch : letters) {
            printStream.print(ch);
        }
        printStream.print("\n");
    }

    public String getUserInput() {
        printStream.print("Enter the letter: ");
        return scanner.nextLine().toLowerCase();
    }

    public boolean handleHint(String input, Word guessWord) {
        if (input.equals("hint")) {
            printStream.println("Hint: " + guessWord.hint());
            return true;
        }
        return false;
    }

    public boolean isValidInput(String input, Set<Character> guessedLetters) {
        if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
            printStream.println("Incorrect input\n");
            return false;
        }

        char guess = input.charAt(0);
        if (guessedLetters.contains(guess)) {
            printStream.println("This letter has already been used\n");
            return false;
        }

        return true;
    }

    public boolean processGuess(char guess, String word, char[] letters) {
        boolean isCorrect = false;
        if (word.contains(String.valueOf(guess))) {
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == guess) {
                    letters[i] = guess;
                    isCorrect = true;
                }
            }
        }
        return isCorrect;
    }

    public boolean checkGameRound(Word guessWord) {
        String word = guessWord.value();
        char[] letters = new char[word.length()];
        Arrays.fill(letters, '_');
        Set<Character> guessedLetters = new HashSet<>();
        int leftAttempts = HangmanFigure.ATTEMPTS;
        boolean isHiddenWord = false;

        while (leftAttempts > 0 && !isHiddenWord) {
            displayGameState(leftAttempts, letters);
            String input = getUserInput();

            if (handleHint(input, guessWord)) {
                continue;
            }

            if (!isValidInput(input, guessedLetters)) {
                continue;
            }

            char guess = input.charAt(0);
            guessedLetters.add(guess);

            if (processGuess(guess, word, letters)) {
                isHiddenWord = word.equals(String.valueOf(letters));
            } else {
                leftAttempts--;
            }
        }
        return isHiddenWord;
    }

}
