package checkout.offer.conditions;

import checkout.Check;

public class TotalCostCondition implements Condition {

    private int totalCost;

    public TotalCostCondition(int totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public boolean checkCondition(Check check) {
        return this.totalCost <= check.getTotalCost();
    }
}
