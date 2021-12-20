package dataBase;

import com.issoft.training.domain.shop.Product;
import com.issoft.training.domain.shop.categories.Category;
import com.issoft.training.stores.utils.RandomStorePopulator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class FillingTable {
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:tcp://localhost/~/test";
    static final String USER = "sa";
    static final String PASS = "";
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private Category category;

    public void fillingTable() {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            List<Category> categoryTable = RandomStorePopulator.createListCategories();
            for (Category category : categoryTable) {
                String SQL = "INSERT INTO CATEGORY (name)" + "VALUES( ?)";
                preparedStatement = connection.prepareStatement(SQL);
                preparedStatement.setString(1, category.getName());
                preparedStatement.executeUpdate();
                switch (category.getName()) {
                    case "food":
                        List<Product> productTable333 = RandomStorePopulator.createListProduct("food");
                        for (Product product : productTable333) {
                            String SQL1 = "INSERT INTO PRODUCT (productName, productPrice, productRate, categoryName)" + "VALUES( ?, ?, ?, ?)";
                            preparedStatement = connection.prepareStatement(SQL1);
                            preparedStatement.setString(1, product.getName());
                            preparedStatement.setInt(2, (int) product.getPrice());
                            preparedStatement.setInt(3, (int) product.getRate());
                            preparedStatement.setString(4, category.getName());
                            preparedStatement.executeUpdate();
                        }
                        break;
                    case "animal":
                        List<Product> productTable222 = RandomStorePopulator.createListProduct("animal");
                        for (Product product : productTable222) {
                            String SQL1 = "INSERT INTO PRODUCT (productName, productPrice, productRate, categoryName)" + "VALUES( ?, ?, ?, ?)";
                            preparedStatement = connection.prepareStatement(SQL1);
                            preparedStatement.setString(1, product.getName());
                            preparedStatement.setInt(2, (int) product.getPrice());
                            preparedStatement.setInt(3, (int) product.getRate());
                            preparedStatement.setString(4, category.getName());
                            preparedStatement.executeUpdate();
                        }
                        break;
                    case "book":
                        List<Product> productTable111 = RandomStorePopulator.createListProduct("book");
                        for (Product product : productTable111) {
                            String SQL1 = "INSERT INTO PRODUCT (productName, productPrice, productRate, categoryName)" + "VALUES( ?, ?, ?, ?)";
                            preparedStatement = connection.prepareStatement(SQL1);
                            preparedStatement.setString(1, product.getName());
                            preparedStatement.setInt(2, (int) product.getPrice());
                            preparedStatement.setInt(3, (int) product.getRate());
                            preparedStatement.setString(4, category.getName());
                            preparedStatement.executeUpdate();
                        }
                        break;
                }
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException se) {
            throw new RuntimeException("Database access error");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
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
