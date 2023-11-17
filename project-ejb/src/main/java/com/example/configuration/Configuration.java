package com.example.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {

    public static Properties getProperties() {
        Properties properties = new Properties();

        try (InputStream input = Configuration.class.getClassLoader().getResourceAsStream("application.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }

}
