package checkout;

import java.time.LocalDate;

public abstract class Offer {

    private LocalDate expirationDate;

    Offer(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
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
