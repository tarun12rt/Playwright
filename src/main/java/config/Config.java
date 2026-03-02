package config;

import java.io.InputStream;
import java.util.Properties;

public class Config {

    private static final Properties prop = new Properties();

    static {
        try (InputStream is =
                     Config.class
                             .getClassLoader()
                             .getResourceAsStream("config.properties")) {

            if (is == null) {
                throw new RuntimeException("config.properties not found in classpath");
            }
            prop.load(is);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String get(String key) {
        return prop.getProperty(key);
    }

    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(prop.getProperty(key));
    }

    // ✅ NEW METHOD (For globalWait)
    public static int getInt(String key) {
        String value = prop.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Key not found in config.properties: " + key);
        }
        return Integer.parseInt(value.trim());
    }

    // ✅ OPTIONAL (Recommended) - Default Value Support
    public static int getInt(String key, int defaultValue) {
        String value = prop.getProperty(key);
        return (value != null) ? Integer.parseInt(value.trim()) : defaultValue;
    }
}