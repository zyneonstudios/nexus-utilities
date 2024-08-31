package com.zyneonstudios.nexus.utilities.strings;

import java.security.SecureRandom;
import java.util.Random;

public class StringGenerator {

    private static final Random random = new Random();
    private static final SecureRandom secureRandom = new SecureRandom();

    public static String generateString(int length) {
        return generate(length, "[a-zA-Z0-9!@#$%^&*()-=_+,./<>?;':\"\\{}|]");
    }

    public static String generateAlphabeticString(int length) {
        return generate(length,"[a-zA-Z]");
    }

    public static String generateAlphanumericString(int length) {
        return generate(length,"[a-zA-Z0-9]");
    }

    public static String generateNumericString(int length) {
        return generate(length,"[0-9]");
    }

    public static String generateUnrestrictedString(int length) {
        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            stringBuilder.append((char) secureRandom.nextInt(Character.MAX_VALUE));
        }
        return stringBuilder.toString();
    }

    public static String generate(int length, String regex) {
        StringBuilder stringBuilder = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            char randomChar;
            do {
                randomChar = (char) random.nextInt(128);
            } while (!String.valueOf(randomChar).matches(regex));
            stringBuilder.append(randomChar);
        }

        return stringBuilder.toString();
    }
}