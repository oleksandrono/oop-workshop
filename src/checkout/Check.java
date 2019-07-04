package checkout;

import java.util.ArrayList;
import java.util.List;

public class Check {
    private List<Product> products = new ArrayList<>();
    private int points = 0;

    private int totalCost = 0;

    public int getTotalCost() {
        return totalCost;
    }

    void discount(int DiscountAmount) {
        totalCost -= DiscountAmount;
    }

    List<Product> getProducts() {
        return products;
    }


    void addProduct(Product product) {
        products.add(product);
        totalCost += product.price;
    }

    public int getTotalPoints() {
        return getTotalCost() + points;
    }

    void addPoints(int points) {
        this.points += points;
    }



    int getCostByCategory(Category category) {
        return products.stream()
                .filter(p -> p.category == category)
                .mapToInt(p -> p.price)
                .reduce(0, (a, b) -> a + b);
    }

    int getCostByTrademark(Trademark trademark){
        return products.stream()
                .filter(p -> p.trademark == trademark)
                .mapToInt(p -> p.price)
                .reduce(0, (a, b) -> a + b);
    }

    int getCountProductsByTrademark(Trademark trademark){
        int count = 0;
        for(Product p : products){
            if(p.trademark==trademark){
                count++;
            }
        }
        return count;
    }

}
