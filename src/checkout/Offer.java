package checkout;

import java.time.LocalDate;

public abstract class Offer {

    private Condition condition;
    private LocalDate expirationDate;

    public Offer(Condition condition, LocalDate expirationDate){
        this.condition = condition;
        this.expirationDate = expirationDate;
    }


    public Offer(LocalDate expirationDate) {
        this(null, expirationDate);

    }

    void useSuitableOffer(Check check) {
        if (this.checkExpirationDate() && isValid(check)) {
            apply(check);
        }
    }


    public abstract void apply(Check check);

    public abstract boolean isValid(Check check);

    private boolean checkExpirationDate() {
        return expirationDate.isAfter(LocalDate.now());
    }
}
