package checkout;

import java.time.LocalDate;
import java.util.List;

public class FactorByCategoryOffer extends Offer {
    private final Category category;
    private final int factor;

    public FactorByCategoryOffer(Category category, int factor, LocalDate expirationDate) {
        super(expirationDate);
        this.category = category;
        this.factor = factor;
    }


    @Override
    public void apply(Check check) {
        int points = check.getCostByCategory(this.category);
        check.addPoints(points * (this.factor - 1));

    }

    @Override
    public boolean isValid(Check check) {
        List<Product> products = check.getProducts();
        for (Product p : products) {
            if (p.category == this.category) {
                return true;
            }
        }
        return false;
    }


}
