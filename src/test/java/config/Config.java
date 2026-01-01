package config;

import java.io.InputStream;
import java.util.Properties;

public class Config {

    private static Properties prop = new Properties();

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
}
