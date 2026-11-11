package hangman;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println();
            System.out.println("Нажми 1, чтобы Начать игру");
            System.out.println("Нажми 2, чтобы Выйти");
            System.out.print("Выбери действие: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    startGame(sc);
                    break;
                case 2:
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
            System.out.println("Ты победил!");
            printSecretWord(secretWord);
        } else {
            System.out.println("Ты проиграл!");
            printSecretWord(secretWord);
        }
        printUsedLetters(usedLetters);

    }

    public static boolean isGameLost(int errors) {
        return errors >= 6;
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
            printUsedLetters(usedLetters);
            System.out.print("Введите букву: ");
            char letter = sc.next().charAt(0);
            if (!isValidLetter(letter, usedLetters)) {
                continue;
            }
            boolean isGuessed = processGuess(letter, secretWord, mask);
            if (isGuessed == false) {
                errors++;
            }
            usedLetters.add(letter);
            System.out.println("Слово: " + Arrays.toString(mask));
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
                words.add(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        }
        return words;
    }

    public static void printSecretWord(String secretWord){
        System.out.println("Загаданное слово было: "+secretWord);
    }

    public static void printUsedLetters(ArrayList<Character>usedLetters){
        System.out.println("Использованные буквы: "+usedLetters);
    }
}
