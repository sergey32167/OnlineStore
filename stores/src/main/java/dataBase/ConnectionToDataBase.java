package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionToDataBase {
    private static volatile Connection instance;

    private ConnectionToDataBase() {
    }

    public static Connection getInstance() {
        String driver = "org.h2.Driver";
        String url = "jdbc:h2:tcp://localhost/~/test";
        String user = "sa";
        String password = "";
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Exception thrown while loading the class", e);
        }
        if (instance == null) {
            try {
                instance = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                throw new RuntimeException("Database access error", e);
            }
        }
        return instance;
    }
}
