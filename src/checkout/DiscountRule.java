package checkout;

public interface DiscountRule {
    Discount getRule(Check check);
}
