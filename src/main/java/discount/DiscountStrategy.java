package discount;

import model.BasketItem;
import model.DiscountLine;

public interface DiscountStrategy {
    DiscountLine calculateDiscount(BasketItem item);
}
