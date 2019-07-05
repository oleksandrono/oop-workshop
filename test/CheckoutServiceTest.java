import checkout.*;
import checkout.offer.bonus.BonusOffer;
import checkout.offer.bonus.FactorReward;
import checkout.offer.bonus.FlatReward;
import checkout.offer.conditions.ByCategoryCondition;
import checkout.offer.conditions.ByTrademarkCondition;
import checkout.offer.conditions.TotalCostCondition;
import checkout.offer.discount.DiscountOffer;
import checkout.offer.discount.PercentDiscount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CheckoutServiceTest {

    private Product milk_7;
    private CheckoutService checkoutService;
    private Product bred_3;
    private Product water_4;
    private LocalDate suitableDate;

    @BeforeEach
    void setUp() {
        checkoutService = new CheckoutService();
        checkoutService.openCheck();

        suitableDate = LocalDate.of(2019, 7, 10);

        milk_7 = new Product(7, "Milk", Category.MILK);
        bred_3 = new Product(3, "Bred", Category.BRED);
        water_4 = new Product(4, "Water", Category.WATER);
    }

    @Test
    void closeCheck__withOneProduct() {
        checkoutService.addProduct(milk_7);
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalCost(), is(7));
    }

    @Test
    void closeCheck__withTwoProducts() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalCost(), is(10));
    }

    @Test
    void addProduct__whenCheckIsClosed__opensNewCheck() {
        checkoutService.addProduct(milk_7);
        Check milkCheck = checkoutService.closeCheck();
        assertThat(milkCheck.getTotalCost(), is(7));

        checkoutService.addProduct(bred_3);
        Check bredCheck = checkoutService.closeCheck();
        assertThat(bredCheck.getTotalCost(), is(3));
    }

    @Test
    void closeCheck__calcTotalPoints() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(10));
    }

    @Test
    void useOffer__addOfferPoints() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        checkoutService.useOffer(new AnyGoodsOffer(6, 2, LocalDate.of(2019, 7, 10)));
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(12));
    }

    @Test
    void useOffer__whenCostLessThanRequired__doNothing() {
        checkoutService.addProduct(bred_3);

        checkoutService.useOffer(new AnyGoodsOffer(6, 2, LocalDate.of(2019, 7, 10)));
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(3));
    }

    @Test
    void useOffer__factorByCategory() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        checkoutService.useOffer(new FactorByCategoryOffer(Category.MILK, 2, LocalDate.of(2019, 7, 10)));
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(31));
    }

    @Test
    void useOffer__beforeAddProduct() {
        checkoutService.addProduct(milk_7);
        checkoutService.useOffer(new FactorByCategoryOffer(Category.MILK, 2, LocalDate.of(2019, 7, 10)));
        checkoutService.addProduct(bred_3);
        checkoutService.addProduct(milk_7);

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(31));
    }

    @Test
    void useOffer__checkExpirationDate__whenDateNormal() {
        checkoutService.addProduct(milk_7);
        checkoutService.useOffer(new FactorByCategoryOffer(Category.MILK, 2, LocalDate.of(2019, 7, 10)));
        checkoutService.addProduct(bred_3);
        checkoutService.addProduct(milk_7);

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(31));
    }

    @Test
    void useOffer__checkExpirationDate__whenDateNotNormalInOneOffer() {
        checkoutService.addProduct(milk_7);
        checkoutService.useOffer(new FactorByCategoryOffer(Category.MILK, 2, LocalDate.of(2019, 7, 1)));
        checkoutService.addProduct(bred_3);
        checkoutService.useOffer(new AnyGoodsOffer(6, 2, LocalDate.of(2019, 7, 10)));
        checkoutService.addProduct(milk_7);

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(19));
    }


    @Test
    void useOffer__discountByCategory() {

        checkoutService.addProduct(water_4);
        checkoutService.useOffer(new DiscountByCategory(Category.WATER, 50, LocalDate.of(2019, 7, 10)));
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(12));
        assertThat(check.getTotalCost(), is(12));
    }

    @Test
    void useOffer__offerByTrademark() {

        milk_7 = new Product(7, "Milk", Trademark.VOLOSHKOVE_POLE);
        checkoutService.useOffer(new OfferByTrademark(Trademark.VOLOSHKOVE_POLE, 2, 5, LocalDate.of(2019, 7, 10)));
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(22));
    }


    @Test
    void useOffer__BonusOffer__allConditionWithFlatReward() {

        milk_7 = new Product(7, "Milk", Trademark.VOLOSHKOVE_POLE);


        checkoutService.useOffer(new BonusOffer((new ByTrademarkCondition(Trademark.VOLOSHKOVE_POLE, 2)), (new FlatReward(5)), suitableDate));
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);

        checkoutService.addProduct(water_4);
        checkoutService.useOffer(new BonusOffer((new ByCategoryCondition(Category.WATER)), (new FlatReward(2)), suitableDate));

        checkoutService.addProduct(bred_3);
        checkoutService.useOffer(new BonusOffer((new TotalCostCondition(20)), (new FlatReward(5)), suitableDate));

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(33));
    }


    @Test
    void useOffer__BonusOffer__allConditionWithFactorReward() {
        bred_3 = new Product(3, "Bred", Trademark.FORMULA_SMAKU);
        milk_7 = new Product(7, "Milk", Category.MILK, Trademark.VOLOSHKOVE_POLE);

        checkoutService.useOffer(new BonusOffer((new ByTrademarkCondition(Trademark.FORMULA_SMAKU, 2)), (new FactorReward(Trademark.FORMULA_SMAKU, 2)), suitableDate));

        checkoutService.addProduct(bred_3);
        checkoutService.addProduct(bred_3);

        checkoutService.addProduct(milk_7);
        checkoutService.useOffer(new BonusOffer((new ByCategoryCondition(Category.MILK)), (new FactorReward(Trademark.VOLOSHKOVE_POLE, 2)), suitableDate));

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(26));
    }

    @Test
    void useOffer__DiscountOffer__allConditionWithFlatReward(){

        water_4 = new Product(4, "Water", Category.WATER, Trademark.MORSHINSKA);
        milk_7 = new Product(7, "Milk", Category.MILK, Trademark.VOLOSHKOVE_POLE);
        Product bred_10 = new Product(10, "Bred", Category.BRED, Trademark.FORMULA_SMAKU);

        checkoutService.useOffer(new DiscountOffer((new ByTrademarkCondition(Trademark.MORSHINSKA, 2)), (new PercentDiscount(Trademark.MORSHINSKA, 50)), suitableDate));
        checkoutService.addProduct(water_4);
        checkoutService.addProduct(water_4);

        checkoutService.useOffer(new DiscountOffer((new ByCategoryCondition(Category.MILK)), (new PercentDiscount(50)), suitableDate));
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_10);

        checkoutService.useOffer(new DiscountOffer((new TotalCostCondition(10)), (new PercentDiscount(50)), suitableDate));

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(13));
    }

}
