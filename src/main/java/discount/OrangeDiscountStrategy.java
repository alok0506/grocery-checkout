package discount;

import model.BasketItem;
import model.DiscountLine;
import model.ItemType;
import org.springframework.stereotype.Component;
import service.PriceCatalog;

import java.math.BigDecimal;

@Component
public class OrangeDiscountStrategy implements DiscountStrategy{
    private final PriceCatalog priceCatalog;

    public OrangeDiscountStrategy(PriceCatalog priceCatalog) {
        this.priceCatalog = priceCatalog;
    }

    @Override
    public DiscountLine calculateDiscount(BasketItem item) {
        if (item.getItemType() != ItemType.ORANGE){
            return new DiscountLine("Orange offer not applicable", BigDecimal.ZERO);
        }
        int quantity = item.getQuantity()/3;
        BigDecimal orangePrice = priceCatalog.getPrice(ItemType.ORANGE);
        BigDecimal normalPriceForThree = orangePrice.multiply(new BigDecimal("3"));
        BigDecimal offerPrice = new BigDecimal("0.75");
        BigDecimal discountPriceForGroup = normalPriceForThree.subtract(offerPrice);
        BigDecimal discount = discountPriceForGroup.multiply(BigDecimal.valueOf(quantity));
        return new DiscountLine("3 Oranges for 0.75",discount);
    }
}
