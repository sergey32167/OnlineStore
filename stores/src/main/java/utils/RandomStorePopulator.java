package utils;
import com.github.javafaker.Faker;
import org.reflections.Reflections;
import shop.Product;
import shop.category.Category;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RandomStorePopulator {
    private static Product createProductByCategoryName(Categorys categoryNames) {
        Faker faker = new Faker();
        switch (categoryNames) {
            case FOOD:
                return new Product(faker.food().vegetable(), faker.number().randomNumber(), faker.number().randomNumber());
            case ANIMAL:
                return new Product(faker.animal().name(), faker.number().randomNumber(), faker.number().randomNumber());
            case BOOK:
                return new Product(faker.book().genre(), faker.number().randomNumber(), faker.number().randomNumber());
            case BEER:
                return new Product(faker.beer().name(), faker.number().randomNumber(), faker.number().randomNumber());
            case COLOR:
                return new Product(faker.color().name(), faker.number().randomNumber(), faker.number().randomNumber());
            default:
                return new Product(faker.name().firstName(), faker.number().randomNumber(), faker.number().randomNumber());
        }
    }

    private static List<Product> createListProduct(Categorys categoryNames) {
        List<Product> listProduct = new ArrayList<>();
        int a1 = (int) (1 + Math.random() * 10);
//        System.out.println(a1);
        for (int a = 0; a < a1; a++) {
            Product productName = createProductByCategoryName(categoryNames);
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
                Class[] params = {String.class};
                Categorys categoryEnum = Categorys.getRandomCategoryName();
                Category newCategory = detector.getConstructor(params).newInstance(categoryEnum.getName());
                List<Product> productList = createListProduct(categoryEnum);
                newCategory.setProductList(productList);
                categoryList.add(newCategory);
//                System.out.println(newCategory);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
//        System.out.println(categoryList);
        return categoryList;
    }

}
