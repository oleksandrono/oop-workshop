package checkout;

import java.time.LocalDate;

public class AnyGoodsOffer extends Offer {
    public final int totalCost;
    public final int points;
    private LocalDate expirationDate;

    public AnyGoodsOffer(int totalCost, int points, LocalDate expirationDate) {
        super();
        this.totalCost = totalCost;
        this.points = points;
        this.expirationDate = expirationDate;
    }


    @Override
    boolean isActual() {
        return expirationDate.isAfter(LocalDate.now());
    }

    @Override
    public void apply(Check check) {
        if (isActual()) {
            if (this.totalCost <= check.getTotalCost()) {
                check.addPoints(this.points);
            } else {
                check.addPoints(0);
            }
        }


    }
}
