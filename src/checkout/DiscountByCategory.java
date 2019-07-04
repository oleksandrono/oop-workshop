package checkout;

import java.time.LocalDate;
import java.util.List;


public class DiscountByCategory extends Offer {


    private final Category category;
    private int discountInPercent;


    public DiscountByCategory(Category category, int discountInPercent, LocalDate expirationDate) {
        super(expirationDate);
        this.category = category;
        this.discountInPercent = discountInPercent;
    }


    @Override
    public void apply(Check check) {
        List<Product> products = check.getProducts();
        for (Product p : products) {
            if (p.category == category) {
                double discountFactor = (double) discountInPercent / 100;
                double priceFactor = p.price;
                double totalDiscount = discountFactor * priceFactor;
                int result = (int) totalDiscount;
                check.applyDiscount(result);
            }
        }

    }

    @Override
    public boolean isValid(Check check) {
        List<Product> products = check.getProducts();
        for (Product p : products) {
            if (p.category == category) {
                return true;
            }
        }
        return false;
    }


}
