package api.support;

import java.util.UUID;

public final class DataGenerator {

    public static String randomEmail(String prefix) {
        return prefix
                + System.currentTimeMillis()
                + "-"
                + UUID.randomUUID().toString().substring(0, 8)
                + "@example.com";
    }
}
