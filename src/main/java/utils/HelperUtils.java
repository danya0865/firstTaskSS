package utils;

import java.util.Random;

public class HelperUtils {
    public static String generatePostCode() {
        Random random = new Random();
        StringBuilder postCode = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            postCode.append(random.nextInt(10));
        }
        return postCode.toString();
    }

    public static String generateFirstName(String postCode) {
        StringBuilder firstName = new StringBuilder();
        for (int i = 0; i < postCode.length(); i += 2) {
            int number = Integer.parseInt(postCode.substring(i, i + 2)) % 26;
            char letter = (char) ('a' + number);
            firstName.append(letter);
        }
        return firstName.toString();
    }
}