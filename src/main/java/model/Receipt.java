package model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

//Represents the final receipt returned by the checkout system.
public class Receipt {
    String pound = "\u00A3";
    private List<ReceiptItem> items;//Stores items included in the receipt.
    private BigDecimal subTotal;//Total price before applying discount.
    private List<DiscountLine> discounts;//Represents individual discounts for each Item.
    private BigDecimal totalDiscount;//Represents total discount price applied of all items.
    @JsonProperty("Total")
    private BigDecimal total;//Final price of all products after applying discount

    public Receipt() {

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

    @Override
    public String toString() {
        StringBuilder receipt = new StringBuilder();
        receipt.append("Item\t\tQuantity\t\tPrice\n");
        receipt.append("------------------------------------\n");

        for (ReceiptItem item : items) {
            receipt.append(item.getItemType())
                    .append("\t\t\t")
                    .append(item.getQuantity())
                    .append("\t\t\t£")
                    .append(item.getLineTotal())
                    .append("\n");
        }
        receipt.append("------------------------------------\n");
        receipt.append("Subtotal:\t\t\t\t\t£")
                .append(subTotal)
                .append("\n\n");
        receipt.append("Discounts:\n");
        for (DiscountLine discount : discounts) {
            receipt.append(String.format("%-27s -£%s%n",
                    discount.getDescription(),
                    discount.getAmount()));
        }
        receipt.append("---------------------------------\n");
        receipt.append("Total Discount:\t\t\t\t-£")
                .append(totalDiscount)
                .append("\n\n");
        receipt.append("Total:\t\t\t\t\t£")
                .append(total);
        return receipt.toString();
    }
}
