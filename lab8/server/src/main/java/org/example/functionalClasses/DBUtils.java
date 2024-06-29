package org.example.functionalClasses;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtils {
    public static Connection getConnection() {
        String dbURL = null;
        String dbUsername = null;
        String dbPassword = null;

        FileInputStream fis;
        Properties properties = new Properties();

        try {
            fis = new FileInputStream("./resources/config.properties");
            properties.load(fis);
            dbURL = properties.getProperty("db.host");
            dbUsername = properties.getProperty("db.username");
            dbPassword = properties.getProperty("db.password");

        } catch (FileNotFoundException e) {
            System.out.println("Поток для конфига не получилось создать.");
        } catch (IOException e) {
            System.out.println("Не удалось считать данные конфига.");
        }

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        } catch (SQLException e) {
            System.out.println("Не удалось подключиться к базе данных: " + e.getMessage());
        }
        return connection;
    }
}
