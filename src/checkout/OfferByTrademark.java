package checkout;

import checkout.offer.Offer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OfferByTrademark extends Offer {
    private final Trademark trademark;
    private final int count;
    private final int points;


    public OfferByTrademark(Trademark trademark, int count, int points, LocalDate expirationDate) {
        super(expirationDate);
        this.trademark = trademark;
        this.count = count;
        this.points = points;
    }


    @Override
    public void apply(Check check) {
        check.addPoints(points);

    }

    @Override
    public boolean isValid(Check check) {
        List<Product> products = check.getProducts();
        List<Product> suitableProducts = new ArrayList<>();
        for (Product p : products) {
            if (p.trademark == trademark) {
                suitableProducts.add(p);
            }
        }
        return suitableProducts.size() >= this.count;

    }


}
