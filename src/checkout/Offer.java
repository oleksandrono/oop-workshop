package checkout;

import java.time.LocalDate;

public abstract class Offer {


    public Offer(LocalDate expirationDate) {
        isActual();
    }

    public Offer() {

    }

    abstract boolean isActual();

    public abstract void apply(Check check);


}
