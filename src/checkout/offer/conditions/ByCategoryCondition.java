package checkout.offer.conditions;

import checkout.Category;
import checkout.Check;
import checkout.Product;

import java.util.List;

public class ByCategoryCondition implements Condition {

    private final Category category;

    public ByCategoryCondition(Category category){
        this.category = category;
    }

    @Override
    public boolean checkCondition(Check check) {
        List<Product> products = check.getProducts();
        for (Product p : products) {
            if (p.category == this.category) {
                return true;
            }
        }
        return false;
    }
}
