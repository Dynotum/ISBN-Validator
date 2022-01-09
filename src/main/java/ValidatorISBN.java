public class ValidatorISBN {

    public boolean checkISBN(String isbn) {
        final char[] isbnArray = isbn.toCharArray();

        if (isbn.length() == 13) {
            int sum = 0;

            for (int i = 0; i < 13; i++) {
                int multplier = i % 2 == 0 ? 1 : 3;
                sum += (multplier * Character.getNumericValue(isbnArray[i]));
            }

            return sum % 10 == 0;

        } else {

            if (isbn.length() != 10) {
                throw new NumberFormatException("ISBN numbers must be 10 digits long");
            }

            int sum = 0;
            int currNum = 10;
            for (int i = 0; i < 10; i++) {

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

            return sum % 11 == 0;
        }
    }
}
