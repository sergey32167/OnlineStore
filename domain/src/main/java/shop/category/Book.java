package shop.category;

public class Book extends Category {

    public Book(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name=" + name +
                ", productList='" + productList + '\'' +
                '}';
    }
}
