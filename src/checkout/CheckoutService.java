package checkout;

import java.util.ArrayList;
import java.util.List;

public class CheckoutService {

    private Check check;

    private List<Offer> offers = new ArrayList<>();

    public void openCheck() {
        check = new Check();
    }

    public void addProduct(Product product) {
        if (check == null) {
            openCheck();
        }
        check.addProduct(product);
    }

    public Check closeCheck() {
        for(Offer offer : offers){
            offer.useSuitableOffer(check);
        }

        Check closedCheck = check;
        check = null;
        return closedCheck;
    }

    void addOffer(Offer offer) {
        offers.add(offer);
    }

    public void useOffer(Offer offer) {
        addOffer(offer);
    }

}
