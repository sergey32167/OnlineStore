package com.issoft.training.dataBase;

import com.issoft.training.domain.shop.Product;
import com.issoft.training.domain.shop.categories.Animal;
import com.issoft.training.domain.shop.categories.Book;
import com.issoft.training.domain.shop.categories.Category;
import com.issoft.training.domain.shop.categories.Food;
import com.issoft.training.stores.utils.Populator;
import com.issoft.training.stores.utils.RandomStorePopulator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseStorePopulator implements Populator {
    private Statement statement;
    private List<Category> categoryListDB;
    private final Connection connection;

    public DataBaseStorePopulator() {
        connection = ConnectionToDataBase.getInstance().getConnection();
    }

    public List<Category> createListCategories() {
        try {
            createTables();
            insertAndGetDataFromTables();
        } catch (SQLException se) {
            throw new RuntimeException("Database error", se);
        } catch (Exception e) {
            throw new RuntimeException("Error", e);
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException se2) {
                throw new RuntimeException("Database access error", se2);
            }
            try {
                if (connection != null) connection.close();
            } catch (SQLException se) {
                throw new RuntimeException("Connection error", se);
            }
        }
        return categoryListDB;
    }

    private void createTables() throws SQLException {
        statement = connection.createStatement();
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
    }

    private void insertAndGetDataFromTables() throws SQLException {
        String sql3 = "SELECT COUNT(1) FROM CATEGORY ";
        ResultSet rs3 = statement.executeQuery(sql3);
        int count = 0;
        while (rs3.next()) {
            count = rs3.getInt(1);
        }
        if (count > 1) {
            getCategoryListFromDB();
        } else {
            insertDataToTables();
            getCategoryListFromDB();
        }
    }

    private void insertDataToTables() throws SQLException {
        List<Category> categoryTable = new RandomStorePopulator().createListCategories();
        for (Category category : categoryTable) {
            String SQL = "INSERT INTO  CATEGORY ( name)  " + "VALUES( ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, category.getName());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            List<Product> productTable = RandomStorePopulator.createListProduct(category.getName());
            for (Product product : productTable) {
                String SQL1 = "INSERT INTO PRODUCT (productName, productPrice, productRate, category_id)" + "VALUES( ?, ?, ?, ?)";
                preparedStatement = connection.prepareStatement(SQL1);
                preparedStatement.setString(1, product.getName());
                preparedStatement.setDouble(2, product.getPrice());
                preparedStatement.setDouble(3, product.getRate());
                preparedStatement.setLong(4, rs.getLong(1));
                preparedStatement.executeUpdate();
            }
        }
    }

    private void getCategoryListFromDB() throws SQLException {
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
            categoryDB.setListProduct(getProductListFromDB(categoryDB.getId()));
        }
    }

    private List<Product> getProductListFromDB(int id) throws SQLException {
        List<Product> productListDB = new ArrayList<>();
        String sql1 = String.format("SELECT * FROM PRODUCT WHERE category_id = '%d' ", id);
        ResultSet rs1 = statement.executeQuery(sql1);
        while (rs1.next()) {
            productListDB.add(new Product(rs1.getString("productName"), rs1.getInt("productPrice"), rs1.getInt("productRate")));
        }
        return productListDB;
    }
}


