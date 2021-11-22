package shop.category;

import utils.RandomStorePopulator;

public class Food extends Category {

    public Food() {
        this.name = "food";
        this.productList = RandomStorePopulator.createListProduct(name);
    }

    @Override
    public String toString() {
        return "Food{" +
                "name=" + name +
                ", productList='" + productList + '\'' +
                '}';
    }
}
