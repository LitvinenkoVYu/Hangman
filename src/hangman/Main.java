package hangman;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public final class Main {
    private static final int MAX_ERRORS = 6;
    private static final int START_GAME = 1;
    private static final int EXIT_GAME = 2;
    private static final String WORDS_FILE = "words.txt";
    private static final char HIDDEN_LETTER = '*';


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println();
            System.out.printf("Нажми %d, чтобы Начать игру\n", START_GAME);
            System.out.printf("Нажми %d, чтобы Выйти\n", EXIT_GAME);
            System.out.print("Выбери действие: ");
            if (!sc.hasNextInt()) {
                sc.nextLine();
                continue;
            }
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

    private static void startGame(Scanner sc) {
        List<String> words = loadWordsFromFile();
        
        String secretWord = getRandomWord(words);
        if (secretWord == null) {
            return;
        }
        char[] mask = new char[secretWord.length()];
        for (int i = 0; i < mask.length; i++) {
            mask[i] = HIDDEN_LETTER;
        }
        int errors = 0;
        List<Character> usedLetters = new ArrayList<>();
        System.out.println("Текущее загаданное слово: ");
        errors = playRound(secretWord, errors, mask, usedLetters, sc);
        if (isGameWon(mask)) {
            HangmanPicture.print(errors);
            System.out.println("Ты победил!");
            printSecretWord(secretWord);
        } else {
            HangmanPicture.print(errors);
            System.out.println("Ты проиграл!");
            printSecretWord(secretWord);
        }
    }

    private static boolean isGameLost(int errors) {
        return errors >= MAX_ERRORS;
    }

    private static boolean isGameWon(char[] mask) {
        for (int i = 0; i < mask.length; i++) {
            if (mask[i] == HIDDEN_LETTER) {
                return false;
            }
        }
        return true;
    }

    private static boolean processGuess(char letter, String secretWord, char[] mask) {
        boolean isGuessed = false;
        for (int i = 0; i < secretWord.length(); i++) {
            if (secretWord.charAt(i) == letter) {
                mask[i] = letter;
                isGuessed = true; // return не делаю, тк продолжаем искать
            }
        }
        return isGuessed;
    }

    private static boolean isValidLetter(char letter, List<Character> usedLetters) {
        return ((letter >= 'а' && letter <= 'я') || letter == 'ё') && !usedLetters.contains(letter);
    }

    private static int playRound(String secretWord, int errors, char[] mask, List<
            Character> usedLetters, Scanner sc) {
        while (!isGameWon(mask) && !isGameLost(errors)) {
            System.out.printf("Количество ошибок: %d/%d%n", errors, MAX_ERRORS);
            HangmanPicture.print(errors);
            printUsedLetters(usedLetters);
            System.out.println("Слово: " + new String(mask));
            System.out.print("Введите букву: ");
            String input = sc.next();
            input = input.toLowerCase();
            if (input.length() > 1) {
                System.out.println("Неверный ввод, вводи по одной букве!");
                continue;
            }
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

    private static String getRandomWord(List<String> words) {
        if (words.size() > 0) {
            Random random = new Random();
            int index = random.nextInt(words.size());
            return words.get(index);
        }
        return null;
    }

    private static List<String> loadWordsFromFile() {
        List<String> words = new ArrayList<>();
        try {
            File file = new File(WORDS_FILE);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String temp = sc.nextLine();
                temp = temp.toLowerCase();
                words.add(temp);
            }
            if (words.isEmpty()) {
                System.out.println("Словарь пуст");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        }
        return words;
    }

    private static void printSecretWord(String secretWord) {
        System.out.println("Загаданное слово было: " + secretWord);
    }

    private static void printUsedLetters(List<Character> usedLetters) {
        System.out.println("Использованные буквы: " + usedLetters);
    }
}

