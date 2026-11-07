package hangman;

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
                    System.out.println("Запуск игры...");
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
}
