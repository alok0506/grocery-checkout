package model;

import java.math.BigDecimal;

public class ReceiptItem {
    private ItemType itemType;
    private int quantity;
    private BigDecimal unitPrice;//price of one item
    private BigDecimal lineTotal;//price of item (quantity * unit price)
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
