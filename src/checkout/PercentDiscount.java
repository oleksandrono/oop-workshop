package checkout;

import java.util.List;
import java.util.function.Predicate;

public class PercentDiscount implements DiscountRule {

    private final int discountPercent;
    private final Category category;
    private final Trademark trademark;

    public PercentDiscount(Category category, Trademark trademark, int discountPercent){
        this.category = category;
        this.trademark = trademark;
        this.discountPercent = discountPercent;
    }

    public PercentDiscount(Category category, int discountPercent){
        this(category, null, discountPercent);
    }

    public PercentDiscount (Trademark trademark, int discountPercent){
        this(null, trademark, discountPercent);
    }

    public PercentDiscount(int discountPercent){
        this(null, null, discountPercent);
    }

    @Override
    public Discount getRule(Check check) {
        int amountDiscount = 0;
        List<Product> products = check.getProducts();
        for (Product p : products) {
            if (category != null) {
                if (p.category == category) {
                    double totalDiscount = ((double) discountPercent / 100) * (double) p.price;
                    amountDiscount = (int) totalDiscount;
                }
            }
            else if(trademark!=null){
                if (p.trademark == trademark) {
                    double totalDiscount = ((double) discountPercent / 100) * (double) p.price;
                    amountDiscount = (int) totalDiscount;
                }
            }
            else {
                double totalDiscount = ((double) discountPercent / 100) * (double) p.price;
                amountDiscount = (int) totalDiscount;
            }

        }
        System.out.println(amountDiscount);
        return new Discount(amountDiscount);
    }
}
