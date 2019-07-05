package checkout.offer.bonus;

import checkout.Check;

public class FlatReward implements Reward {


    private int points;

    public FlatReward(int points) {
        this.points = points;
    }

    @Override
    public int getReward(Check check) {
        int result = 0;
        if (points <= check.getTotalCost()) {
            result += points;
        }
        return result;
    }
}
