package utilities;

import core.GlobalConstants;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesConfig {
    private final Properties properties;
    private final String propertyFilePath = GlobalConstants.ENVIRONMENT_CONFIG_PATH + "env-%s.properties";

    // private static variable
    private static PropertiesConfig configLoader;

    //private contructor
    private PropertiesConfig() {
        properties = PropertiesConfig.propertyLoader(propertyFilePath);
    }

    //Public static method
    public static synchronized PropertiesConfig getFileConfigLoader() {
        if (configLoader == null) {
            configLoader = new PropertiesConfig();
        }
        return configLoader;
    }

    private static Properties propertyLoader(String propertyFilePath) {
        Properties properties = new Properties();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Faile to load properties file" + propertyFilePath);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration properties not found at" + propertyFilePath);
        }
        return properties;
    }

    public String getApplicationUrl() {
        String url = properties.getProperty("App.Url");
        if (url != null) return url;
        else throw new RuntimeException("Url not specified in the properties file.");
    }

    public String getApplicationUserName() {
        String username = properties.getProperty("App.User");
        if (username != null) return username;
        else throw new RuntimeException("Username not specified in the properties file.");
    }

    public String getApplicationPassword() {
        String password = properties.getProperty("App.Pass");
        if (password != null) return password;
        else throw new RuntimeException("Password not specified in the properties file.");
    }
}
