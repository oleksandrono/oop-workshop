package checkout;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OfferByTrademark extends Offer {
    private final Trademark trademark;
    private final int count;
    private final int points;
    private LocalDate expirationDate;


    public OfferByTrademark(Trademark trademark, int count, int points, LocalDate expirationDate) {
        this.trademark = trademark;
        this.count = count;
        this.points = points;
        this.expirationDate = expirationDate;
    }


    @Override
    boolean isActual() {
        return expirationDate.isAfter(LocalDate.now());
    }

    @Override
    public void apply(Check check) {
        if (isActual()) {
            List<Product> products = check.getProducts();
            List<Product> suitableProducts = new ArrayList<>();

            for (Product p : products) {
                if (p.trademark == trademark) {
                    suitableProducts.add(p);
                }
            }
            if (suitableProducts.size() >= this.count) {
                check.addPoints(points);
            }
        }
    }
}
