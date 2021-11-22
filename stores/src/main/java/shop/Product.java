package shop;
public class Product {

    private String pName;
    private double price;
    private double rate;

    public Product(String pName, double price, double rate) {
        this.pName = pName;
        this.price = price;
        this.rate = rate;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Product{"
                + "pName='" + pName + '\''
                + ", price=" + price
                + ", rate=" + rate
                + '}';
    }
}
