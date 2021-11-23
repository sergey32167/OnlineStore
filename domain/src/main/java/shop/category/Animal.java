package shop.category;

public class Animal extends Category {

    public Animal(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name=" + name +
                ", productList='" + productList + '\'' +
                '}';
    }
}
