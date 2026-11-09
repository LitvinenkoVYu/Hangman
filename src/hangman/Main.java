package hangman;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileSystemAlreadyExistsException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
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

    public static void startGame(Scanner scanner) {
        ArrayList<String> words = loadWordsFromFile(); // получил словарь
        String secretWord = getRandomWord(words); // получил слово из словаря
        char[] mask = new char[secretWord.length()];
        for (int i = 0; i < mask.length; i++) {
            mask[i] = '*';
        }
        int errors=0;
        ArrayList<Character>usedLetters=new ArrayList<>();
        System.out.println("Загадано: " + Arrays.toString(mask));  // перевод с языка массивов на обычный язык
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
}
