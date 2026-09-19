package model;

public class BasketItem {
    private ItemType itemType;
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
