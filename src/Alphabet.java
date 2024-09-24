public class Alphabet {
    // Приватная константа ALPHABET, которая содержит буквы русского алфавита и спецсимволы
    private static final char[] ALPHABET = {
            'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з',
            'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р',
            'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'ю', 'я', '.', ',', '«',
            '»', '"', '\'', ':', '!', '?', ' '
    };

    public String getAlphabet() {
        return new String(ALPHABET);
    }

    public int getIndex(char character) {
        for (int i = 0; i < ALPHABET.length; i++) {
            if (ALPHABET[i] == character) {
                return i;
            }
        }
        return -1; // Символ не найден
    }

    public char getCharacter(int index) {
        if (index < 0 || index >= ALPHABET.length) {
            throw new IllegalArgumentException("Индекс вне диапазона");
        }
        return ALPHABET[index];
    }

    public char shiftCharacter(char character, int shift) {
        int index = getIndex(character);
        if (index == -1) {
            return character; // Неизменяем символы, которых нет в алфавите
        }
        int newIndex = (index + shift + ALPHABET.length) % ALPHABET.length;
        return getCharacter(newIndex);
    }
}

