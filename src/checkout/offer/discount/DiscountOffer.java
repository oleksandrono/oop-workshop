package checkout.offer.discount;

import checkout.Check;
import checkout.offer.Offer;
import checkout.offer.conditions.Condition;

import java.time.LocalDate;

public class DiscountOffer extends Offer {

    private final DiscountRule discount;
    private final Condition condition;

    public DiscountOffer(Condition condition, DiscountRule discount, LocalDate expirationDate) {
        super(condition, expirationDate);
        this.discount = discount;
        this.condition = condition;
    }

    @Override
    public void apply(Check check) {
        check.applyDiscount(this.discount.getRule(check).discountAmount);
    }

    @Override
    public boolean isValid(Check check) {
        return this.condition.checkCondition(check);
    }
}
