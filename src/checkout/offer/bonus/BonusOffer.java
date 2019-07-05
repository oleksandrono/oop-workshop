package checkout.offer.bonus;

import checkout.Check;
import checkout.offer.conditions.Condition;
import checkout.offer.Offer;

import java.time.LocalDate;

public class BonusOffer extends Offer {

    private final Condition condition;
    private Reward reward;

    public BonusOffer(Condition condition, Reward reward, LocalDate expirationDate) {
        super(condition, expirationDate);
        this.condition = condition;
        this.reward = reward;
    }

    @Override //reward
    public void apply(Check check) {
        check.addPoints(reward.getReward(check));
    }

    @Override //condition
    public boolean isValid(Check check) {
        return condition.checkCondition(check);
    }
}
