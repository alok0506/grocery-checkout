package service;

import discount.BananaDiscountStrategy;
import discount.OrangeDiscountStrategy;
import exception.InvalidCheckoutException;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CheckoutServiceTest {

    private CheckoutService checkoutService;

    @BeforeEach
    void setUp(){
        PriceCatalog priceCatalog = new PriceCatalog();
        BananaDiscountStrategy bananaDiscountStrategy = new BananaDiscountStrategy(priceCatalog);
        OrangeDiscountStrategy orangeDiscountStrategy = new OrangeDiscountStrategy(priceCatalog);
        this.checkoutService = new CheckoutService(
                priceCatalog,List.of(bananaDiscountStrategy,orangeDiscountStrategy)
        );
    }
    @Test
    void calculateSubTotal(){
        PriceCatalog priceCatalog = mock(PriceCatalog.class);
        when(priceCatalog.getPrice(ItemType.BANANA)).thenReturn(new BigDecimal("0.50"));
        when(priceCatalog.getPrice(ItemType.APPLE)).thenReturn(new BigDecimal("0.60"));
        when(priceCatalog.getPrice(ItemType.ORANGE)).thenReturn(new BigDecimal("0.30"));
        CheckoutService checkoutService = new CheckoutService(priceCatalog, List.of());
        CheckoutRequest checkoutRequest = new CheckoutRequest(
                List.of(
                        new BasketItem(ItemType.BANANA,3),
                        new BasketItem(ItemType.ORANGE,4),
                        new BasketItem(ItemType.APPLE,1)
                        ));
        BigDecimal subTotal = checkoutService.calculateSubTotal(checkoutRequest);
        assertEquals(new BigDecimal("3.30"),subTotal);
    }

    @Test
    void shouldCalculateDiscounts(){
        CheckoutRequest request = new CheckoutRequest(List.of(new BasketItem(ItemType.BANANA,3),
                new BasketItem(ItemType.ORANGE,4),new BasketItem(ItemType.APPLE,1)));
        List<DiscountLine> discounts = checkoutService.calculateDiscounts(request);
        assertEquals(2,discounts.size());
        assertEquals(new BigDecimal("0.50"),discounts.get(0).getAmount());
        assertEquals(new BigDecimal("0.15"),discounts.get(1).getAmount());
    }

    @Test
    void shouldCalculateCompleteReceipt(){
        CheckoutRequest request = new CheckoutRequest(List.of(new BasketItem(ItemType.BANANA,3),
                new BasketItem(ItemType.ORANGE,4),new BasketItem(ItemType.APPLE,1)));
        Receipt receipt = checkoutService.calculateReceipt(request);
        assertEquals(new BigDecimal("3.30"),receipt.getSubTotal());
        assertEquals(new BigDecimal("0.65"),receipt.getTotalDiscount());
        assertEquals(new BigDecimal("2.65"),receipt.getTotal());
    }

    @Test
    void shouldThrowExceptionWhenBasketIsEmpty(){
        CheckoutRequest request = new CheckoutRequest(List.of());
        assertThrows(InvalidCheckoutException.class,()->checkoutService.calculateReceipt(request));
    }
}
