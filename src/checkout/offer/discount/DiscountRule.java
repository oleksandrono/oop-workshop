package checkout.offer.discount;

import checkout.Check;

public interface DiscountRule {
    Discount getRule(Check check);
}
