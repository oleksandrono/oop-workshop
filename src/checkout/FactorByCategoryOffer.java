package checkout;

import java.time.LocalDate;

public class FactorByCategoryOffer extends Offer {
    public final Category category;
    public final int factor;
    private LocalDate expirationDate;

    public FactorByCategoryOffer(Category category, int factor, LocalDate expirationDate) {
        super();
        this.category = category;
        this.factor = factor;
        this.expirationDate = expirationDate;
    }

    @Override
    boolean isActual() {
        return expirationDate.isAfter(LocalDate.now());
    }

    @Override
    public void apply(Check check) {
        if (isActual()) {
            int points = check.getCostByCategory(this.category);
            check.addPoints(points * (this.factor - 1));
        } else {
            check.addPoints(0);
        }
    }
}
