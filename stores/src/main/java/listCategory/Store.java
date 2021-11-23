package listCategory;
import shop.category.Category;
import utils.RandomStorePopulator;
import java.util.List;

public class Store {
    private List<Category> categoryList;

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public void treatmentListCategory() {
        for (Category category : RandomStorePopulator.createListCategory()) {
            System.out.println(category);
        }
    }

}
