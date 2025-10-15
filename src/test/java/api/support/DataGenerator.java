package api.support;

import java.util.Random;
import java.util.UUID;

public final class DataGenerator {

    private static final Random RANDOM = new Random();

    public static String randomEmail(String prefix) {
        return prefix
                + System.currentTimeMillis()
                + "-"
                + UUID.randomUUID().toString().substring(0, 8)
                + "@example.com";
    }

    public static String randomFirstName() {
        return randomString(6, 10);// random 6–10 character name
    }

    public static String randomLastName() {
        return randomString(6, 12); // random 6–12 character name
    }

    private static String randomString(int minLen, int maxLen) {
        int length = RANDOM.nextInt(maxLen - minLen + 1) + minLen;
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            char c = (char) ('a' + RANDOM.nextInt(26));
            sb.append(c);
        }

        // Capitalize the first letter
        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        return sb.toString();
    }
}
