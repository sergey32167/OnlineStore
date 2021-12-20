package dataBase;

import com.issoft.training.domain.shop.Product;
import com.issoft.training.domain.shop.categories.Animal;
import com.issoft.training.domain.shop.categories.Book;
import com.issoft.training.domain.shop.categories.Category;
import com.issoft.training.domain.shop.categories.Food;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReadTable {
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:tcp://localhost/~/test";
    static final String USER = "sa";
    static final String PASS = "";
    private Connection connection = null;
    private Statement statement = null;
    private List<Category> categoryListDB;

    public List<Category> readTable() {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();

            categoryListDB = new ArrayList<>();

            String sql = "SELECT * FROM CATEGORY ";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String categoryName = rs.getString("name");
                switch (categoryName) {
                    case ("food"):
                        Food food = new Food();
                        categoryListDB.add(food);
                        break;
                    case ("book"):
                        Book book = new Book();
                        categoryListDB.add(book);
                        break;
                    case ("animal"):
                        Animal animal = new Animal();
                        categoryListDB.add(animal);
                        break;
                }
            }

            for (Category categoryDB : categoryListDB) {
                categoryDB.setListProduct(fillProdListDB(categoryDB.getName()));
            }
//            System.out.println(categoryListDB);

            rs.close();
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
        return categoryListDB;
    }

    public List<Product> fillProdListDB(String categoryName) {
        List<Product> productListDB = new ArrayList<>();
        String sql1 = String.format("SELECT * FROM PRODUCT WHERE categoryName = '%s' ", categoryName);
        ResultSet rs1 = null;
        try {
            rs1 = statement.executeQuery(sql1);
        } catch (SQLException e) {
            throw new RuntimeException("Database access error");
        }
        while (true) {
            try {
                if (!rs1.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException("Database access error");
            }
            try {
                productListDB.add(new Product(rs1.getString("productName"), rs1.getInt("productPrice"), rs1.getInt("productRate")));
            } catch (SQLException e) {
                throw new RuntimeException("Database access error");
            }
        }
//        System.out.println(productListDB);
        return productListDB;
    }


}

