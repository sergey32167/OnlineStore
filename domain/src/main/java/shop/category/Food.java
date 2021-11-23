package shop.category;

public class Food extends Category {

    public Food(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Food{" +
                "name=" + name +
                ", productList='" + productList + '\'' +
                '}';
    }
}
