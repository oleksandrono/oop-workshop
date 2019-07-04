package checkout;

import java.time.LocalDate;
import java.util.List;


public class DiscountByCategory extends Offer {


    public final Category category;
    private int discountInPercent;
    private LocalDate expirationDate;


    public DiscountByCategory(Category category, int discountInPercent, LocalDate expirationDate) {
        this.category = category;
        this.discountInPercent = discountInPercent;
        this.expirationDate = expirationDate;
    }


    @Override
    boolean isActual() {
        return expirationDate.isAfter(LocalDate.now());
    }

    @Override
    public void apply(Check check) {
        if (isActual()) {
            List<Product> products = check.getProducts();
            for (Product p : products) {
                if (p.category == category) {
                    double discountFactor = (double) discountInPercent / 100;
                    double priceFactor = p.price;
                    double totalDiscount = discountFactor * priceFactor;
                    int result = (int) totalDiscount;
                    check.discount(result);
                }
            }
        }
    }
}
