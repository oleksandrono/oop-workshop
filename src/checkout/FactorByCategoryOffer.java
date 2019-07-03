package checkout;

import java.time.LocalDate;

public class FactorByCategoryOffer extends Offer {
    public final Category category;
    public final int factor;

    public FactorByCategoryOffer(Category category, int factor, LocalDate expiredDate) {
        super(expiredDate);
        this.category = category;
        this.factor = factor;
    }



    @Override
    public void apply(Check check) {
        if(checkExpiryDate()) {
            int points = check.getCostByCategory(this.category);
            check.addPoints(points * (this.factor - 1));
        }
        else{
            check.addPoints(0);
        }
    }
}
