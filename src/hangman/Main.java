package hangman;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public final class Main {
    public static final int MAX_ERRORS = 6;
    public static final int START_GAME = 1;
    public static final int EXIT_GAME = 2;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println();
            System.out.println("Нажми 1, чтобы Начать игру");
            System.out.println("Нажми 2, чтобы Выйти");
            System.out.print("Выбери действие: ");
            int choice = sc.nextInt();
            switch (choice) {
                case START_GAME:
                    startGame(sc);
                    break;
                case EXIT_GAME:
                    System.out.println("Выход из игры...");
                    return;
                default:
                    System.out.println("Неверный ввод");
                    break;
            }
        }
    }

    public static void startGame(Scanner sc) {
        ArrayList<String> words = loadWordsFromFile(); // получил словарь
        String secretWord = getRandomWord(words); // получил слово из словаря
        char[] mask = new char[secretWord.length()];
        for (int i = 0; i < mask.length; i++) {
            mask[i] = '*';
        }
        int errors = 0;
        ArrayList<Character> usedLetters = new ArrayList<>();
        System.out.println("Текущее загаданное слово: ");  // перевод с языка массивов на обычный язык
        errors = playRound(secretWord, errors, mask, usedLetters, sc);
        if (isGameWon(mask) == true) {
            HangmanPicture.printHangman(errors);
            System.out.println("Ты победил!");
            printSecretWord(secretWord);
        } else {
            HangmanPicture.printHangman(errors);
            System.out.println("Ты проиграл!");
            printSecretWord(secretWord);
        }
    }

    public static boolean isGameLost(int errors) {
        return errors >= MAX_ERRORS;
    }

    public static boolean isGameWon(char[] mask) {
        for (int i = 0; i < mask.length; i++) {
            if (mask[i] == '*') {
                return false;
            }
        }
        return true;
    }

    public static boolean processGuess(char letter, String secretWord, char[] mask) {
        boolean isGuessed = false;
        for (int i = 0; i < secretWord.length(); i++) {
            if (secretWord.charAt(i) == letter) {
                mask[i] = letter;
                isGuessed = true; // return не делаю, тк продолжаем искать
            }
        }
        return isGuessed;
    }
    // letter - введена игроком
    // secretWord - загаданное компьютером

    public static boolean isValidLetter(char letter, ArrayList<Character> usedLetters) {
        return Character.isLetter(letter) && !usedLetters.contains(letter);
    }

    public static int playRound(String secretWord, int errors, char[] mask, ArrayList<
            Character> usedLetters, Scanner sc) {
        while (!isGameWon(mask) && !isGameLost(errors)) { // вызов true, если игра закончена
            System.out.println("Количество ошибок: " + errors + "/6");
            HangmanPicture.printHangman(errors);
            printUsedLetters(usedLetters);
            System.out.println("Слово: " + Arrays.toString(mask));
            System.out.print("Введите букву: ");
            // char letter = sc.next().charAt(0);
            String input = sc.next();
            input = input.toLowerCase();
            char letter = input.charAt(0);
            if (!isValidLetter(letter, usedLetters)) {
                continue;
            }
            boolean isGuessed = processGuess(letter, secretWord, mask);
            if (isGuessed == false) {
                errors++;
                System.out.println("Упс, такой буквы нет!");
            }
            usedLetters.add(letter);
            System.out.println();
        }
        return errors;
    }

    public static String getRandomWord(ArrayList<String> words) {
        if (words.size() > 0) {
            Random random = new Random();
            int index = random.nextInt(words.size());
            return words.get(index);
        } else {
            return null;
        }
    }

    public static ArrayList<String> loadWordsFromFile() {
        ArrayList<String> words = new ArrayList<>();
        try {
            File file = new File("words.txt");
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String temp = sc.nextLine();
                temp = temp.toLowerCase();
                words.add(temp);
                //words.add(sc.nextLine()); это если мы работаем без понижения регистра
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        }
        return words;
    }

    public static void printSecretWord(String secretWord) {
        System.out.println("Загаданное слово было: " + secretWord);
    }

    public static void printUsedLetters(ArrayList<Character> usedLetters) {
        System.out.println("Использованные буквы: " + usedLetters);
    }
}

class HangmanPicture {
    public HangmanPicture() {
    } // предотвращение создание объекта извне

    public static final String[] PICTURES = {"",
            """
          +---+
          |   |
              |
              |
              |
              |
        =========
        """,
            """
          +---+
          |   |
          O   |
              |
              |
              |
        =========
        """,
            """
          +---+
          |   |
          O   |
          |   |
              |
              |
        =========
        """,
            """
          +---+
          |   |
          O   |
         /|   |
              |
              |
        =========
        """,
            """
          +---+
          |   |
          O   |
         /|\\  |
              |
              |
        =========
        """,
            """
          +---+
          |   |
          O   |
         /|\\  |
         / \\  |
              |
        =========
        """};

    public static void printHangman(int errors) {
        if (errors >= 0 && errors <= PICTURES.length - 1) {
            System.out.println(PICTURES[errors]);
        }
    }
}
