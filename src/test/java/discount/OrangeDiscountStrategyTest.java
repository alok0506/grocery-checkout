package discount;

import model.BasketItem;
import model.DiscountLine;
import model.ItemType;
import org.junit.jupiter.api.Test;
import service.PriceCatalog;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrangeDiscountStrategyTest {
    @Test
    void shouldGiveDiscountFor3Oranges(){
        PriceCatalog priceCatalog = new PriceCatalog();
        OrangeDiscountStrategy strategy = new OrangeDiscountStrategy(priceCatalog);
        BasketItem item = new BasketItem(ItemType.ORANGE,3);
        DiscountLine discountLine = strategy.calculateDiscount(item);
        assertEquals(new BigDecimal("0.15"),discountLine.getAmount());
    }

    @Test
    void shouldGiveDiscountFor6Oranges(){
        PriceCatalog priceCatalog = new PriceCatalog();
        OrangeDiscountStrategy strategy = new OrangeDiscountStrategy(priceCatalog);
        BasketItem item = new BasketItem(ItemType.ORANGE,6);
        DiscountLine discountLine = strategy.calculateDiscount(item);
        assertEquals(new BigDecimal("0.30"),discountLine.getAmount());
    }
}
