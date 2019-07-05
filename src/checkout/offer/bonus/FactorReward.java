package checkout.offer.bonus;

import checkout.Category;
import checkout.Check;
import checkout.Trademark;

public class FactorReward implements Reward {


    private final Category category;
    private final int factor;
    private final Trademark trademark;

    public FactorReward(Category category, int factor) {
        this.category = category;
        this.factor = factor;
        this.trademark = null;
    }

    public FactorReward(Trademark trademark, int factor){
        this.trademark = trademark;
        this.factor = factor;
        this.category = null;
    }

    @Override
    public int getReward(Check check) {
        int points = 0;
        if (category!=null){
            points = check.getCostByCategory(this.category) * (this.factor - 1);
        }
        else if(trademark!=null){
            points = check.getCostByTrademark(this.trademark) * (this.factor - 1);
        }
        return points;
    }
}
