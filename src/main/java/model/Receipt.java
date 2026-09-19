package model;

import java.math.BigDecimal;
import java.util.List;

public class Receipt {
    private List<ReceiptItem> items;
    private BigDecimal subTotal;
    private  List<DiscountLine> discounts;
    private BigDecimal totalDiscount;
    private BigDecimal total;
    public Receipt(){

    }

    public Receipt(List<ReceiptItem> items, BigDecimal subTotal, List<DiscountLine> discounts, BigDecimal totalDiscount, BigDecimal total) {
        this.items = items;
        this.subTotal = subTotal;
        this.discounts = discounts;
        this.totalDiscount = totalDiscount;
        this.total = total;
    }

    public List<ReceiptItem> getItems() {
        return items;
    }

    public void setItems(List<ReceiptItem> items) {
        this.items = items;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public List<DiscountLine> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<DiscountLine> discounts) {
        this.discounts = discounts;
    }

    public BigDecimal getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(BigDecimal totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
