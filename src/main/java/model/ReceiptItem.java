package model;

import java.math.BigDecimal;

public class ReceiptItem {
    private ItemType itemType;//Product name/type
    private int quantity;//Number of units Purchased
    private BigDecimal unitPrice;//Price of one item
    private BigDecimal lineTotal;//Price of item (quantity * unit price)
    public ReceiptItem(){

    }

    public ReceiptItem(ItemType itemType, int quantity, BigDecimal unitPrice, BigDecimal lineTotal) {
        this.itemType = itemType;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.lineTotal = lineTotal;
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

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getLineTotal() {
        return lineTotal;
    }

    public void setLineTotal(BigDecimal lineTotal) {
        this.lineTotal = lineTotal;
    }
}
