import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static final int LIVES_COUNT = 6;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Меню:");
            System.out.println("1. Почати гру");
            System.out.println("2. Вийти");
            System.out.print("Оберіть опцію: ");
            int choice = sc.nextInt();

            if (choice == 1) {
                File file = new File("package", "file.txt");
                String randomWord = getRandomWord(file);
                playGame(randomWord, sc);
            } else if (choice == 2) {
                System.out.println("Дякуємо за гру!");
                break;
            } else {
                System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
        }
    }

    private static String getRandomWord(File file) {
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException("Помилка читання файлу: " + e.getMessage(), e);
        }

        String[] words = result.toString().split("\n");
        if (words.length == 0) {
            throw new RuntimeException("Файл порожній або не містить слів.");
        }
        return words[(int) (Math.random() * words.length)].toUpperCase();
    }

    private static void playGame(String randomWord, Scanner sc) {
        int tries = 0;
        StringBuilder maskedWord = new StringBuilder(randomWord.replaceAll(".", "*"));
        List<Character> usedLetters = new ArrayList<>();

        System.out.println(maskedWord);

        while (tries < LIVES_COUNT) {
            System.out.print("Введіть літеру: ");
            char letter = sc.next().toUpperCase().charAt(0);

            if (!usedLetters.isEmpty()) {
                System.out.print("Ви вже вводили: ");
                usedLetters.forEach(l -> System.out.print(l + " "));
                System.out.println();
            }

            if (usedLetters.contains(letter)) {
                System.out.println("Ви вже вводили цю літеру.");
                continue;
            }

            usedLetters.add(letter);

            if (randomWord.contains(String.valueOf(letter))) {
                for (int i = 0; i < randomWord.length(); i++) {
                    if (randomWord.charAt(i) == letter) {
                        maskedWord.setCharAt(i, letter);
                    }
                }
                if (maskedWord.indexOf("*") == -1) {
                    System.out.println("Ви перемогли! Слово: " + randomWord);
                    return;
                }
            } else {
                System.out.println("Невірно, спробуй ще.");
                tries++;
                drawHangman(tries);
                if (tries == LIVES_COUNT) {
                    System.out.println("Ви програли! Загадане слово: " + randomWord);
                    return;
                }
            }
            System.out.println(maskedWord);
        }
    }

    private static void drawHangman(int tries) {
        String[] stages = {
                """
          +---+
          |   |
              |
              |
              |
              |
        =========
        """,   // 0 помилок

                """
          +---+
          |   |
          O   |
              |
              |
              |
        =========
        """,   // 1 помилка

                """
          +---+
          |   |
          O   |
          |   |
              |
              |
        =========
        """,   // 2 помилки

                """
          +---+
          |   |
          O   |
         /|   |
              |
              |
        =========
        """,   // 3 помилки

                """
          +---+
          |   |
          O   |
         /|\\  |
              |
              |
        =========
        """,   // 4 помилки

                """
          +---+
          |   |
          O   |
         /|\\  |
         /    |
              |
        =========
        """,   // 5 помилок

                """
          +---+
          |   |
          O   |
         /|\\  |
         / \\  |
              |
        =========
        """    // 6 помилок (програш)
        };
        System.out.println(stages[tries]);
    }
}
