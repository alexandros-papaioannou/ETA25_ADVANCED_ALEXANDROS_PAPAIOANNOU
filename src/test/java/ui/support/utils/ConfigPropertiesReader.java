package ui.support.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class ConfigPropertiesReader {

    private static final Properties properties;

    static {
        properties = new Properties();
        try {
            Path configPath = Path.of("src/test/java/resources/config/config.properties");
            properties.load(Files.newBufferedReader(configPath));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties file", e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
