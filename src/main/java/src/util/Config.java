package src.util;

import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static Properties props = new Properties();

    static {
        try (InputStream input = Config.class.getClassLoader().getResourceAsStream("Config.properties")) {
            if (input == null) {
                throw new RuntimeException("File config.properties tidak ditemukan!");
            }
            props.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Gagal load config.properties", e);
        }
    }

    public static String get(String configQuery) {
        return props.getProperty(configQuery);
    }
}
