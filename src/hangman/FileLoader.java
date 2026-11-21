package hangman;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileLoader {
    public static List<String> loadWordsFromFile(String filePath) {
        List<String> words = new ArrayList<>();
        try {
            File file = new File(filePath);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String temp = sc.nextLine();
                temp = temp.toLowerCase();
                words.add(temp);
            }

        } catch (FileNotFoundException e) {

            return words;
        }
        return words;
    }
}
