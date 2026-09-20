package model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class BasketItem {
    @NotNull(message = "Item type is required")
    private ItemType itemType;
    @Positive(message = "Quantity should be greater than zero")
    private int quantity;

    public BasketItem(ItemType itemType, int quantity) {
        this.itemType = itemType;
        this.quantity = quantity;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
