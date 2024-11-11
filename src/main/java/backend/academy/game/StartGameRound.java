package backend.academy.game;

import backend.academy.input.InputHandler;
import backend.academy.word.WordChoosing;
import java.util.Scanner;

public class StartGameRound {
    private final ConsolePrint consolePrint = new ConsolePrint();
    private final InputHandler inputHandler = new InputHandler(new Scanner(System.in));
    private final WordChoosing wordChoosing = new WordChoosing(inputHandler);
    private final GameController gameController = new GameController(System.out, new Scanner(System.in));

    public void startGameRound() {
        consolePrint.printMessageToStartGameRound(wordChoosing.difficulty(), wordChoosing.category());
        boolean checkGuessing = gameController.checkGameRound(wordChoosing.guessWord());
        consolePrint.printMessageToEndTheGame(wordChoosing.guessWord(), checkGuessing);
    }
}
