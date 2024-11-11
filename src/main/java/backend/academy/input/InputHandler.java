package backend.academy.input;

import backend.academy.game.ConsolePrint;
import backend.academy.word.Word;
import backend.academy.word.WordReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import lombok.Getter;

public class InputHandler {
    private final Scanner scanner;
    private final PrintStream printStream = System.out;
    @Getter private Map<String, Map<String, List<Word>>> wordList = new HashMap<>();
    @Getter private List<String> categories;
    private final ConsolePrint consolePrint = new ConsolePrint();

    public InputHandler(Scanner scanner) {
        this.scanner = scanner;
        try {
            WordReader wordReader = new WordReader();
            wordList = wordReader.loadWordsFromFile();
            categories = new ArrayList<>(wordList.keySet());
        } catch (IOException e) {
            printStream.println("Error reading the file: " + e.getMessage());
        }
    }

    public String chooseCategory() {
        consolePrint.printMessageToChooseCategory();
        for (String category : categories()) {
            printStream.println("* " + category);
        }
        String category;
        do {
            category = scanner.nextLine().toLowerCase();
            if (category.isEmpty()) {
                category = categories.get(new Random().nextInt(categories.size()));
                break;
            } else if (wordList.containsKey(category)) {
                break;
            } else {
                printStream.println("Incorrect category, please, choose again");
            }
        }
        while (true);

        return category;
    }

    public String chooseDifficulty() {
        consolePrint.printMessageToChooseDifficulty();
        String difficulty;
        do {
            difficulty = scanner.nextLine().toUpperCase();
            if (difficulty.isEmpty()) {
                difficulty = Difficulties
                    .values()[new Random().nextInt(Difficulties.values().length)]
                    .toString();
                break;
            } else {
                try {
                    Difficulties.valueOf(difficulty);
                    break;
                } catch (IllegalArgumentException e) {
                    printStream.println("Incorrect difficulty, please, choose again");
                }
            }
        }
        while (true);

        return difficulty.toLowerCase();
    }

}
