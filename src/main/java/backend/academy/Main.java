package backend.academy;

import backend.academy.game.ConsolePrint;
import backend.academy.game.StartGameRound;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    public static void main(String[] args) {
        new ConsolePrint().printWelcome();
        new StartGameRound().startGameRound();

    }
}
