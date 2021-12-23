package dataBase;

import com.issoft.training.stores.listCategories.Store;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionToDataBase {
    private static volatile ConnectionToDataBase instance;

    private ConnectionToDataBase() {
    }

    public static ConnectionToDataBase getInstance() {
        ConnectionToDataBase result = instance;
        if (result != null) {
            return result;
        }
        synchronized (Store.class) {
            if (instance == null) {
                instance = new ConnectionToDataBase();
            }
            return instance;
        }
    }

    public Connection connectionToDataBase() {
        String driver = "org.h2.Driver";
        String url = "jdbc:h2:tcp://localhost/~/test";
        String user = "sa";
        String password = "";
        Connection connection;
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
