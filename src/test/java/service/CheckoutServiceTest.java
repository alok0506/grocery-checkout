package service;

import model.BasketItem;
import model.CheckoutRequest;
import model.ItemType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CheckoutServiceTest {
    @Test
    void calculateSubTotal(){
        PriceCatalog priceCatalog = mock(PriceCatalog.class);
        when(priceCatalog.getPrice(ItemType.BANANA)).thenReturn(new BigDecimal("0.50"));
        when(priceCatalog.getPrice(ItemType.APPLE)).thenReturn(new BigDecimal("0.60"));
        when(priceCatalog.getPrice(ItemType.ORANGE)).thenReturn(new BigDecimal("0.30"));
        CheckoutService checkoutService = new CheckoutService(priceCatalog);
        CheckoutRequest checkoutRequest = new CheckoutRequest(
                List.of(
                        new BasketItem(ItemType.BANANA,3),
                        new BasketItem(ItemType.ORANGE,4),
                        new BasketItem(ItemType.APPLE,1)
                        ));
        BigDecimal subTotal = checkoutService.calculateSubTotal(checkoutRequest);
        assertEquals(new BigDecimal("3.30"),subTotal);
    }
}
