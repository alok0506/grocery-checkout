package service;

import model.BasketItem;
import model.CheckoutRequest;
import model.ItemType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CheckoutService {
    private PriceCatalog priceCatalog;

    public CheckoutService(PriceCatalog priceCatalog) {
        this.priceCatalog = priceCatalog;
    }

    public BigDecimal calculateSubTotal(CheckoutRequest checkoutRequest){
        BigDecimal subTotal = BigDecimal.ZERO;
        for(BasketItem item:checkoutRequest.getItem()){
            ItemType itemType = item.getItemType();
            int quantity = item.getQuantity();

            BigDecimal unitPrice = priceCatalog.getPrice(itemType);
            BigDecimal total = unitPrice.multiply(BigDecimal.valueOf(quantity));
            subTotal = subTotal.add(total);
        }
        return subTotal;
    }
}
