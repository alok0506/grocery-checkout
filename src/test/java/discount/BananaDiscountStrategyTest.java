package discount;

import model.BasketItem;
import model.DiscountLine;
import model.ItemType;
import org.junit.jupiter.api.Test;
import service.PriceCatalog;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class BananaDiscountStrategyTest {
    @Test
    void shouldGiveOneFreeBananaWhenCustomerBuys3(){
        PriceCatalog priceCatalog = new PriceCatalog();
        BananaDiscountStrategy strategy = new BananaDiscountStrategy(priceCatalog);
        BasketItem item = new BasketItem(ItemType.BANANA,3);
        DiscountLine discountLine = strategy.calculateDiscount(item);
        assertEquals(new BigDecimal("0.50"),discountLine.getAmount());
    }

    @Test
    void shouldGiveTwoFreeBananaWhenCustomerBuys6(){
        PriceCatalog priceCatalog = new PriceCatalog();
        BananaDiscountStrategy strategy = new BananaDiscountStrategy(priceCatalog);
        BasketItem item = new BasketItem(ItemType.BANANA,6);
        DiscountLine discountLine = strategy.calculateDiscount(item);
        assertEquals(new BigDecimal("1.00"),discountLine.getAmount());
    }
}
