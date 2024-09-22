public class Alphabet {
    // Здесь создаём приватную константу ALPHABET, которая содержит буквы русского алфавита в нижнем и верхнем
    // регистрах, а также спецсимволы
    private static final String ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя" +
            "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ" +
            " .,!?\"'-:;()";

    public String getAlphabet() {
        return ALPHABET;
    }

    public int getIndex(char character) {
        return ALPHABET.indexOf(character);
    }

    public char getCharacter(int index) {
        if (index < 0 || index >= ALPHABET.length()) {
            throw new IllegalArgumentException("Индекс вне диапазона");
        }
        return ALPHABET.charAt(index);
    }

    public char shiftCharacter(char character, int shift) {
        int index = getIndex(character);
        if (index == -1) {
            return character; // Неизменяем символы, которых нет в алфавите
        }
        int newIndex = (index + shift + ALPHABET.length()) % ALPHABET.length();
        return getCharacter(newIndex);
    }
}
