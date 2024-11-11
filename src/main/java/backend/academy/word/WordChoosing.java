package backend.academy.word;

import backend.academy.input.InputHandler;
import java.util.Random;
import lombok.Getter;

public class WordChoosing {
    private final InputHandler inputHandler;
    @Getter private final Word guessWord;
    @Getter private final String category;
    @Getter private final String difficulty;

    public WordChoosing(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
        this.difficulty = inputHandler.chooseDifficulty();
        this.category = inputHandler.chooseCategory();
        this.guessWord = chooseWord(category, difficulty);
    }

    public Word chooseWord(String category, String difficulty) {
        Word word;
        word = inputHandler.wordList().get(category).get(difficulty)
            .get(new Random().nextInt(inputHandler.wordList().get(category).get(difficulty).size()));
        return word;
    }
}
