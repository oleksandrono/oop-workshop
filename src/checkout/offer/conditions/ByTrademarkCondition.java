package checkout.offer.conditions;


import checkout.Check;
import checkout.Trademark;

public class ByTrademarkCondition implements Condition {

    private Trademark trademark;
    private int count;

    public ByTrademarkCondition(Trademark trademark, int count) {
        this.trademark = trademark;
        this.count = count;
    }

    @Override
    public boolean checkCondition(Check check) {
        if (check.getCountProductsByTrademark(trademark) >= count) {
            return true;
        }
        return false;
    }

}
