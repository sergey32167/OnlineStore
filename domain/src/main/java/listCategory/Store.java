package listCategory;
import shop.category.Category;
import utils.RandomStorePopulator;
import java.io.*;
import java.util.List;

public class Store {
    private List<Category> categoryList;

    public Store() {
        categoryList = RandomStorePopulator.createListCategory();
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public void handListCategory() throws IOException {

        File file = new File("shop.txt");
        file.createNewFile();
        FileWriter writer = new FileWriter(file);

        for (Category category : categoryList) {
            category.setProductList(RandomStorePopulator.createListProduct(category.getName()));
            System.out.println(category);

            writer.write(String.valueOf(category));
            writer.flush();

        }
        writer.close();
    }

}
