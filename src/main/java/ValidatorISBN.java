public class ValidatorISBN {

    public static final int LONG_ISBN_LENGTH = 13;
    public static final int SHORT_ISBN_LENGTH = 10;
    public static final int SHORT_ISBN_MULTIPLIER = 11;
    public static final int LONG_ISBN_MULTIPLIER = 10;

    public boolean checkISBN(String isbn) {
        final char[] isbnArray = isbn.toCharArray();

        if (isbn.length() == LONG_ISBN_LENGTH) {
            return isValidLongDigitISBN(isbnArray);
        } else if (isbn.length() == SHORT_ISBN_LENGTH) {
            return isValidShortDigitsISBN(isbnArray);
        }
        throw new NumberFormatException("ISBN numbers must be 10 or 13 digits long");
    }

    private boolean isValidShortDigitsISBN(char[] isbnArray) {
        int sum = 0;
        int currNum = 10;
        for (int i = 0; i < SHORT_ISBN_LENGTH; i++) {

            if (i == 9 && isbnArray[i] == 'X') {
                sum += 10;
                continue;
            }

            if (!Character.isDigit(isbnArray[i])) {
                throw new NumberFormatException("ISBN numbers must be digits,");
            }

            sum += (Character.getNumericValue(isbnArray[i]) * currNum);
            currNum--;
        }

        return sum % SHORT_ISBN_MULTIPLIER == 0;
    }

    private boolean isValidLongDigitISBN(char[] isbnArray) {
        int sum = 0;

        for (int i = 0; i < LONG_ISBN_LENGTH; i++) {
            int multiplier = i % 2 == 0 ? 1 : 3;
            sum += (multiplier * Character.getNumericValue(isbnArray[i]));
        }

        return sum % LONG_ISBN_MULTIPLIER == 0;
    }
}