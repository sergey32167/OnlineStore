package dataBase;

import com.issoft.training.domain.shop.Product;
import com.issoft.training.domain.shop.categories.Animal;
import com.issoft.training.domain.shop.categories.Book;
import com.issoft.training.domain.shop.categories.Category;
import com.issoft.training.domain.shop.categories.Food;
import com.issoft.training.stores.utils.RandomStorePopulator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataBaseStorePopulator {
    private Statement statement;
    private PreparedStatement preparedStatement = null;
    private List<Category> categoryListDB;

    public List<Category> createListCategoryDB() {
        DataBaseConnect dataBaseConnect = new DataBaseConnect();
        try {
            statement = dataBaseConnect.connection().createStatement();
            String sql1 = "CREATE TABLE IF NOT EXISTS CATEGORY " +
                    "(id INTEGER not NULL AUTO_INCREMENT," +
                    "name VARCHAR(50) NOT NULL, " +
                    " PRIMARY KEY ( id ))";
            statement.executeUpdate(sql1);
            String sql2 = "CREATE TABLE IF NOT EXISTS PRODUCT " +
                    "(id INTEGER not NULL AUTO_INCREMENT, " +
                    " productName VARCHAR(50), " +
                    " productPrice DOUBLE, " +
                    " productRate DOUBLE, " +
                    " category_id LONG NOT NULL, " +
                    " FOREIGN KEY (category_id) REFERENCES CATEGORY (id), " +
                    " PRIMARY KEY ( id ))";
            statement.executeUpdate(sql2);

            String sql3 = "SELECT id FROM CATEGORY ";
            ResultSet rs3 = statement.executeQuery(sql3);
            int count = 0;
            while (rs3.next()) {
                count++;
            }
//            System.out.println(count);

            if (count > 1) {
                fillCategoryDB();
//
            } else {
                List<Category> categoryTable = new RandomStorePopulator().createListCategories();
                for (Category category : categoryTable) {
                    String SQL = "INSERT INTO  CATEGORY ( name)  " + "VALUES( ?)";
                    preparedStatement = dataBaseConnect.connection().prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
                    preparedStatement.setString(1, category.getName());
                    preparedStatement.executeUpdate();
                    ResultSet rs = preparedStatement.getGeneratedKeys();
                    rs.next();
                    List<Product> productTable = RandomStorePopulator.createListProduct(category.getName());
                    for (Product product : productTable) {
                        String SQL1 = "INSERT INTO PRODUCT (productName, productPrice, productRate, category_id)" + "VALUES( ?, ?, ?, ?)";
                        preparedStatement = dataBaseConnect.connection().prepareStatement(SQL1);
                        preparedStatement.setString(1, product.getName());
                        preparedStatement.setDouble(2, product.getPrice());
                        preparedStatement.setDouble(3, product.getRate());
                        preparedStatement.setLong(4, rs.getLong(1));
                        preparedStatement.executeUpdate();
                    }
                }
                fillCategoryDB();
            }

            statement.close();
            dataBaseConnect.connection().close();
        } catch (SQLException se) {
            throw new RuntimeException("Database access error", se);
        } catch (Exception e) {
            throw new RuntimeException("Error", e);
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException se2) {
                throw new RuntimeException("Database access error", se2);
            }
            try {
                if (dataBaseConnect.connection() != null) dataBaseConnect.connection().close();
            } catch (SQLException se) {
                throw new RuntimeException("Database access error", se);
            }
        }
//        System.out.println(categoryListDB);
        return categoryListDB;
    }

    private void fillCategoryDB() throws SQLException {
        categoryListDB = new ArrayList<>();
        String sql = "SELECT name, id FROM CATEGORY ";
        ResultSet rs = statement.executeQuery(sql);
        Category category = null;
        while (rs.next()) {
            String categoryName = rs.getString("name");
            switch (categoryName) {
                case ("food"):
                    category = new Food();
                    break;
                case ("book"):
                    category = new Book();
                    break;
                case ("animal"):
                    category = new Animal();
                    break;
            }
            category.setId(rs.getInt("id"));
            categoryListDB.add(category);
        }

        for (Category categoryDB : categoryListDB) {
            categoryDB.setListProduct(fillProdListDB(categoryDB.getId()));
        }
    }

    private List<Product> fillProdListDB(int id) {
        List<Product> productListDB = new ArrayList<>();
        String sql1 = String.format("SELECT * FROM PRODUCT WHERE category_id = '%d' ", id);
        ResultSet rs1 = null;
        try {
            rs1 = statement.executeQuery(sql1);
        } catch (SQLException e) {
            throw new RuntimeException("Database access error", e);
        }
        while (true) {
            try {
                if (!rs1.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException("Database access error", e);
            }
            try {
                productListDB.add(new Product(rs1.getString("productName"), rs1.getInt("productPrice"), rs1.getInt("productRate")));
            } catch (SQLException e) {
                throw new RuntimeException("Database access error", e);
            }
        }
        return productListDB;
    }
}


