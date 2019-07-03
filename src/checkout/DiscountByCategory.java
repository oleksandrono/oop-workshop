package checkout;

import java.time.LocalDate;
import java.util.List;


public class DiscountByCategory extends Offer {




    public final Category category;


    public DiscountByCategory(Category category, LocalDate expiredDate) {
        super(expiredDate);
        this.category=category;
    }


    @Override
    public void apply(Check check) {
        if(checkExpiryDate()) {
            List<Product> products = check.getProducts();
            for(Product p : products) {
                if (p.category == category) {
                    check.discount(p.price / 2);
                }
            }
        }
    }
}
