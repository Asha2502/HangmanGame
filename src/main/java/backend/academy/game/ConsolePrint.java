package backend.academy.game;

import backend.academy.input.Difficulties;
import backend.academy.word.Word;
import java.io.PrintStream;

public class ConsolePrint {
    private final PrintStream printStream = System.out;

    public void printWelcome() {
        printStream.println("Welcome to Hangman!");
    }

    public void printMessageToChooseDifficulty() {
        printStream.print("Choose the difficulty (");
        for (Difficulties df : Difficulties.values()) {
            printStream.print(df.toString().toLowerCase() + ";");
        }
        printStream.print(") or press Enter for random mode:\n");
    }

    public void printMessageToChooseCategory() {
        printStream.println("Choose category or press Enter for random mode: ");
    }

    public void printMessageToStartGameRound(String difficulty, String category) {
        printStream.println("\nChoosen difficulty and category: " + difficulty + ", " + category);
        printStream.println("Let's start!");
        printStream.println("Print 'hint' if you want to get hint");
    }

    public void printMessageToEndTheGame(Word guessWord, boolean checkGuessing) {
        if (checkGuessing) {
            printStream.println("\nCongratulations! You guessed the word: " + guessWord.value());
        } else {
            printStream.println(HangmanFigure.HANGMAN[HangmanFigure.ATTEMPTS]);
            printStream.println("\nYou've lost :( The hidden word was: " + guessWord.value());
        }
    }
}
