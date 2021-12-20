package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {

    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:tcp://localhost/~/test";
    static final String USER = "sa";
    static final String PASS = "";
    private Connection connection = null;
    private Statement statement = null;

    public void createTables() {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();

            String sql = "DROP TABLE IF EXISTS PRODUCT";
            String sql1 = "DROP TABLE IF EXISTS CATEGORY";
            String sql2 = "CREATE TABLE CATEGORY " +
                    "(name VARCHAR(50) NOT NULL, " +
                    " PRIMARY KEY ( name ))";
            statement.executeUpdate(sql);
            statement.executeUpdate(sql1);
            statement.executeUpdate(sql2);

            String sql3 = "DROP TABLE IF EXISTS PRODUCT";
            String sql4 = "CREATE TABLE PRODUCT " +
                    "(id INTEGER not NULL AUTO_INCREMENT, " +
                    " productName VARCHAR(50), " +
                    " productPrice INTEGER, " +
                    " productRate INTEGER, " +
                    " categoryName VARCHAR(50), " +
                    " FOREIGN KEY (categoryName) REFERENCES CATEGORY (name), " +
                    " PRIMARY KEY ( id ))";
            statement.executeUpdate(sql3);
            statement.executeUpdate(sql4);

            statement.close();
            connection.close();
        } catch (SQLException se) {
            throw new RuntimeException("Database access error");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException se2) {
                throw new RuntimeException("Database access error");
            }
            try {
                if (connection != null) connection.close();
            } catch (SQLException se) {
                throw new RuntimeException("Database access error");
            }
        }
    }
}


