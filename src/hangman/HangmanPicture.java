package hangman;

public class HangmanPicture {
    public HangmanPicture() {
    };
    public static final String[] PICTURES = {
            "",
            """







          =================
        """,
            """
            +----------+
            |
            |
            |
            |
            |
            |
          =================
        """,
            """
            +----------+
            |
            |               O
            |              /|\\
            |              / \\
            |
            |
          =================
        """,
            """
            +----------+
            |
            |
            |
            |          O
            |         /|\\
            |         / \\
          =================
        """,
            """
            +----------+
            |          |
            |          |
            |          O
            |         /|\\
            |         / \\
            |        [___]
          =================
        """,
            """
            +----------+
            |          |
            |          |
            |          O
            |         /|\\
            |         / \\
            |              __
          ================= /_/
        """
    };

    public static void print(int errors) {
            System.out.println(PICTURES[errors]);
    }
}


