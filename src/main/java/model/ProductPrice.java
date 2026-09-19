package model;

import java.math.BigDecimal;

public class ProductPrice {
    private final ItemType itemType;
    private final BigDecimal price;

    public ProductPrice(ItemType itemType, BigDecimal price) {
        this.itemType = itemType;
        this.price = price;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
