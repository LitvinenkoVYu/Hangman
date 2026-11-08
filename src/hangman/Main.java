package hangman;

import java.util.ArrayList;
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
        System.out.println("Игра началась!");
    }

    public static String getRandomWord(ArrayList<String> words) {
        if(words.size()>0) {
            Random random=new Random();
            int index=random.nextInt(words.size());
            return words.get(index);
        } else {
            return null;
        }
    }

}
