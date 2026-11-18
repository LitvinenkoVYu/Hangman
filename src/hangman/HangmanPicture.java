package hangman;

public class HangmanPicture {
    public HangmanPicture() {
    }

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
        if (errors >= 0 && errors <= PICTURES.length - 1) {
            System.out.println(PICTURES[errors]);
        }
    }
}


