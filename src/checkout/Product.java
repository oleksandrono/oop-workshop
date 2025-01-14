package checkout;

public class Product {
    public final int price;
    final String name;
    public Trademark trademark;
    public Category category;

    public Product(int price, String name, Category category, Trademark trademark) {
        this.price = price;
        this.name = name;
        this.category = category;
        this.trademark = trademark;
    }

    public Product(int price, String name, Category category) {
        this(price, name, category, null);
    }

    public Product(int price, String name, Trademark trademark) {
        this(price, name, null, trademark);
    }

    public Product(int price, String name) {
        this(price, name, null, null);
    }
}