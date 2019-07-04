package checkout;

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
