package checkout;

import checkout.offer.Offer;

import java.time.LocalDate;

public class AnyGoodsOffer extends Offer {
    public final int totalCost;
    public final int points;

    public AnyGoodsOffer(int totalCost, int points, LocalDate expirationDate) {
        super(expirationDate);
        this.totalCost = totalCost;
        this.points = points;
    }


    @Override
    public void apply(Check check) {
        if (this.totalCost <= check.getTotalCost()) {
            check.addPoints(this.points);
        } else {
            check.addPoints(0);
        }

    }

    @Override
    public boolean isValid(Check check) {
        return true;
    }

}
