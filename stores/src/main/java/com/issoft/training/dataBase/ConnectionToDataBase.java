package com.issoft.training.dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionToDataBase {
    private static ConnectionToDataBase instance;
    private Connection connection = null;

    private ConnectionToDataBase() {
    }

    public static ConnectionToDataBase getInstance() {
        if (instance == null) {
            instance = new ConnectionToDataBase();
        }
        return instance;
    }

    public Connection getConnection() {
        if (connection == null) {
            String driver = "org.h2.Driver";
            String url = "jdbc:h2:tcp://localhost/~/test";
            String user = "sa";
            String password = "";
            try {
                Class.forName(driver);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("Exception thrown while loading the class", e);
            }
            try {
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                throw new RuntimeException("Database access error", e);
            }
        }
        return connection;
    }
}
