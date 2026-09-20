package discount;

import model.BasketItem;
import model.DiscountLine;
import model.ItemType;
import org.springframework.stereotype.Component;
import service.PriceCatalog;

import java.math.BigDecimal;
@Component
public class BananaDiscountStrategy implements DiscountStrategy{
    private final PriceCatalog priceCatalog;

    public BananaDiscountStrategy(PriceCatalog priceCatalog) {
        this.priceCatalog = priceCatalog;
    }

    @Override
    public DiscountLine calculateDiscount(BasketItem item) {
        if(item.getItemType() != ItemType.BANANA){
            return new DiscountLine("No BANANA discount", BigDecimal.ZERO);
        }
        int freeItems = item.getQuantity()/3;
        BigDecimal bananaPrice = priceCatalog.getPrice(ItemType.BANANA);
        BigDecimal discount = bananaPrice.multiply(BigDecimal.valueOf(freeItems));
        return new DiscountLine("Buy 2 Get 1 Free - Bananas",discount);
    }
}
