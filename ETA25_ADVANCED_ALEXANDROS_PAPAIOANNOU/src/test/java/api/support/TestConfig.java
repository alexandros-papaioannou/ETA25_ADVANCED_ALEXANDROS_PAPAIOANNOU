package api.support;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestConfig {

    //on class initialization, it immediately calls loadProperties().
    //stores the result in a Properties object (props).
    //the configuration file is read only once and cached for all subsequent calls.
    private static final Properties props = loadProperties();


    //looks up a value in the loaded props.
    //if not found → throws an error (IllegalArgumentException) so tests fail fast
    //instead of continuing with a missing config.
    //trims whitespace from the value (avoids issues if someone left spaces in the file).
    public static String getProperty(String key) {
        String v = props.getProperty(key);
        if (v == null) throw new IllegalArgumentException("Missing property: " + key);
        return v.trim();
    }


    //loads a file named config/api.properties from the classpath
    //if the file isn’t found → throws an error right away.
    //reads all key-value pairs into a Properties object.
    //returns it to be stored in props.
    private static Properties loadProperties() {
        try (InputStream is = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream("config/api.properties")) {
            if (is == null) throw new IllegalStateException("config/api.properties not found on classpath");
            Properties p = new Properties();
            p.load(is);
            return p;
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config/api.properties", e);
        }
    }
}
