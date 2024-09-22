import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
// Это наш класс CaesarCipher, который содержит метод main - входную точку в наше приложение
// На всякий случай, файл, который мы шифруем называется input.txt, а выходной файл с зашифрованным текстом называется output.txt
// также, есть файл decrypted_output.txt, который отображает текст после расшифровки. Все файлы хранятся
// в папке src/

public class CaesarCipher {
    // Здесь у нас создаётся экземпляр класса Alphabet для работы с алфавитом, используемым в шифре Цезаря
    private final Alphabet alphabet = new Alphabet();
        // Наш метод main, который будет запрашивать у пользователя одно из 8 действий.
        // От себя я добавил отображение содержимого входных и выходных файлов, пункты 5-7
    private static final String INPUT_FILE = "src/data/input/input.txt";
    private static final String OUTPUT_FILE = "src/data/output/output.txt";
    private static final String DECRYPTED_FILE = "src/data/decrypted_output/decrypted_output.txt";
    private static final String STATISTICAL_FILE = "src/data/statistics/statistics.txt";
    private static final String OUTPUT_BRUTEFORCE_FILE = "src/data/output_bruteforce/output_bruteforce.txt";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CaesarCipher cipher = new CaesarCipher();

        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1. Шифрование");
            System.out.println("2. Расшифровка");
            System.out.println("3. Brute force");
            System.out.println("4. Статистический анализ");
            System.out.println("5. Отобразить данные входного файла");
            System.out.println("6. Отобразить данные выходного файла");
            System.out.println("7. Отобразить данные выходного расшифрованного файла");
            System.out.println("0. Выход");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    encrypt(scanner, cipher);
                    break;
                case "2":
                    decrypt(scanner, cipher);
                    break;
                case "3":
                    bruteForce(cipher);
                    break;
                case "4":
                    statisticalAnalysis(cipher);
                    break;
                case "5":
                    displayInputFileData();
                    break;
                case "6":
                    displayOutputFileData();
                    break;
                case "7":
                    displayOutputOfDecryptedFileData();
                    break;
                case "0":
                    System.out.println("Выход из программы.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }
// Метод, который непосредственно шифрует содержимое входного файла и записывает зашифрованные данные
    // в файл output.txt
    private static void encrypt(Scanner scanner, CaesarCipher cipher) {
        System.out.print("Введите ключ (1-33): ");
        int key = Integer.parseInt(scanner.nextLine());

        try {
            cipher.validateInput(INPUT_FILE);
            cipher.encrypt(INPUT_FILE, OUTPUT_FILE, key);
            System.out.println("Шифрование завершено.");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
    // Метод, который непосредственно дешифрует содержимое файла output.txt
    private static void decrypt(Scanner scanner, CaesarCipher cipher) {
        String inputFile = OUTPUT_FILE;
        String outputFile = DECRYPTED_FILE; // Изменен на отдельный файл
        System.out.print("Введите ключ (1-33): ");

        try {
            int key = Integer.parseInt(scanner.nextLine());

            // Проверка на корректность ключа
            if (key < 1 || key > 33) {
                throw new IllegalArgumentException("Ключ должен быть в диапазоне от 1 до 33.");
            }

            cipher.validateInput(inputFile);
            cipher.decrypt(inputFile, outputFile, key);
            System.out.println("Расшифровка завершена. Результат сохранен в " + outputFile);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: Введите целое число для ключа.");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
    //Здесь я создал метод bruteForce, который запрашивает у пользователя имена входного (input.txt) и выходного файлов (output.txt), а также выполняет расшифровку
    // с перебором всех возможных ключей, используя метод bruteForce класса CaesarCipher
    private static void bruteForce(CaesarCipher cipher) {
        try {
            cipher.validateInput(OUTPUT_FILE);
            cipher.bruteForce(OUTPUT_FILE, OUTPUT_BRUTEFORCE_FILE);
            System.out.println("Brute force успешно завершён!!!");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
// Здесь у нас происходит статистический анализ, который запрашивает у пользователя имена входного и выходного файлов и выполняет статистический анализ,
//// который подсчитывает частоту появления символов в тексте, используя метод statisticalAnalysis класса CaesarCipher.
    private static void statisticalAnalysis(CaesarCipher cipher) {
        try {
            cipher.validateInput(INPUT_FILE);
            cipher.statisticalAnalysis(INPUT_FILE, STATISTICAL_FILE);
            System.out.println("Статистический анализ завершен.");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
// Здесь я создал метод, который отображает содержимое входного файла input.txt
    private static void displayInputFileData() {
        try {
            String content = new String(Files.readAllBytes(Paths.get(INPUT_FILE)));
            System.out.println("Содержимое входного файла:");
            System.out.println(content);
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
    // Здесь я создал метод, который отображает содержимое выходного файла output.txt
    private static void displayOutputFileData() {
        try {
            String content = new String(Files.readAllBytes(Paths.get(OUTPUT_FILE)));
            System.out.println("Содержимое выходного файла:");
            System.out.println("=======================================");
            System.out.println(content);
            System.out.println("=======================================");
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
    private static void displayOutputOfDecryptedFileData() {
        try {
            String content = new String(Files.readAllBytes(Paths.get(DECRYPTED_FILE)));
            System.out.println("Содержимое выходного расшифрованного файла:");
            System.out.println("=======================================");
            System.out.println(content);
            System.out.println("=======================================");
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
    public void encrypt(String inputFile, String outputFile, int key) throws IOException {
        String text = readFile(inputFile);
        String encryptedText = caesarCipher(text, key);
        writeFile(outputFile, encryptedText);
    }

    public void decrypt(String inputFile, String outputFile, int key) throws IOException {
        encrypt(inputFile, outputFile, alphabet.getAlphabet().length() - key);
    }

    public void bruteForce(String inputFile, String outputFile) throws IOException {
        String text = readFile(inputFile);
        for (int key = 1; key < alphabet.getAlphabet().length(); key++) {
            String decryptedText = caesarCipher(text, key);
            writeFile(outputFile.replace(".txt", "_brute_" + key + ".txt"), decryptedText);
        }
    }

    public void statisticalAnalysis(String inputFile, String outputFile) throws IOException {
        String text = readFile(inputFile);
        Map<Character, Integer> frequencyMap = new HashMap<>();

        for (char ch : text.toLowerCase().toCharArray()) {
            if (alphabet.getAlphabet().indexOf(ch) != -1) {
                frequencyMap.put(ch, frequencyMap.getOrDefault(ch, 0) + 1);
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue());
                writer.newLine();
            }
        }
    }

    private String caesarCipher(String text, int key) {
        StringBuilder result = new StringBuilder();
        for (char ch : text.toCharArray()) {
            result.append(alphabet.shiftCharacter(ch, key)); //
        }
        return result.toString();
    }

    private String readFile(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8);
    }

    private void writeFile(String filePath, String text) throws IOException {
        Files.write(Paths.get(filePath), text.getBytes(StandardCharsets.UTF_8));
    }

    private void validateInput(String filePath) throws FileNotFoundException {
        if (!new File(filePath).exists()) {
            throw new FileNotFoundException("Файл '" + filePath + "' не найден.");
        }
    }
}
