package utils;
import com.github.javafaker.Faker;
import org.reflections.Reflections;
import shop.Product;
import shop.category.Category;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RandomStorePopulator {

    private static Product createProductByCategoryName(String categoryName) {
        Faker faker = new Faker();
        switch (categoryName) {
            case "food":
                return new Product(faker.food().vegetable(), faker.number().randomNumber(), faker.number().randomNumber());
            case "animal":
                return new Product(faker.animal().name(), faker.number().randomNumber(), faker.number().randomNumber());
            case "book":
                return new Product(faker.book().genre(), faker.number().randomNumber(), faker.number().randomNumber());
        }
        return new Product(faker.name().firstName(), faker.number().randomNumber(), faker.number().randomNumber());
    }

    public static List<Product> createListProduct(String categoryName) {
        List<Product> listProduct = new ArrayList<>();
        int a1 = (int) (1 + Math.random() * 10);
//        System.out.println(a1);
        for (int a = 0; a < a1; a++) {
            Product productName = createProductByCategoryName(categoryName);
            listProduct.add(productName);
        }
//        System.out.println(listProduct);
        return listProduct;
    }

    public static List<Category> createListCategory() {
        List<Category> categoryList = new ArrayList<>();
        Reflections reflections = new Reflections("shop");
        Set<Class<? extends Category>> subTypes = reflections.getSubTypesOf(Category.class);
        for (Class<? extends Category> detector : subTypes) {
            try {
                Category newObj = detector.newInstance();
                categoryList.add(newObj);
//                System.out.println(newObj);
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
//        System.out.println(categoryList);
        return categoryList;
    }

}
