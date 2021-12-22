package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnect {
    private final String driver = "org.h2.Driver";
    private final String url = "jdbc:h2:tcp://localhost/~/test";
    private final String user = "sa";
    private final String password = "";
    private Connection connection;

    public Connection connection() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Exception thrown while loading the class", e);
        } catch (SQLException e) {
            throw new RuntimeException("Database access error", e);
        }
        return connection;
    }
}
