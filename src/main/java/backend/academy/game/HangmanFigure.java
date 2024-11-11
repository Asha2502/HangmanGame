package backend.academy.game;

import lombok.experimental.UtilityClass;

@UtilityClass
public class HangmanFigure {
    public static final String[] HANGMAN = {
        "",
        """
        \n++++++
        |
        |
        |
        |
        |
        ++++++++++""",
        """
        \n++++++
        |    |
        |
        |
        |
        |
        ++++++++++""",
        """
        \n++++++
        |    |
        |    O
        |
        |
        |
        ++++++++++""",
        """
        \n++++++
        |    |
        |    O
        |   /
        |
        |
        ++++++++++""",
        """
        \n++++++
        |    |
        |    O
        |   /|\\
        |
        |
        ++++++++++""",
        """
        \n++++++
        |    |
        |    O
        |   /|\\
        |   /
        |
        ++++++++++""",
        """
        \n++++++
        |    |
        |    O
        |   /|\\
        |   / \\
        |
        ++++++++++"""
    };
    public static final int ATTEMPTS = HANGMAN.length - 1;
}
