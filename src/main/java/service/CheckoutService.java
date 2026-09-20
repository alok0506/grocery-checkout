package service;

import discount.DiscountStrategy;
import model.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import java.math.BigDecimal;

@Service
public class CheckoutService {
    private final PriceCatalog priceCatalog;
    private final List<DiscountStrategy> discountStrategies;

    public CheckoutService(PriceCatalog priceCatalog, List<DiscountStrategy> discountStrategies) {
        this.priceCatalog = priceCatalog;
        this.discountStrategies = discountStrategies;
    }

    public BigDecimal calculateSubTotal(CheckoutRequest checkoutRequest){
        BigDecimal subTotal = BigDecimal.ZERO;
        for(BasketItem item:checkoutRequest.getItems()){
            ItemType itemType = item.getItemType();
            int quantity = item.getQuantity();

            BigDecimal unitPrice = priceCatalog.getPrice(itemType);
            BigDecimal total = unitPrice.multiply(BigDecimal.valueOf(quantity));
            subTotal = subTotal.add(total);
        }
        return subTotal;
    }

    public List<DiscountLine> calculateDiscounts(CheckoutRequest request){
        List<DiscountLine> discountLines = new ArrayList<>();
        for (BasketItem item : request.getItems()){
            for (DiscountStrategy strategy : discountStrategies){
                DiscountLine discount = strategy.calculateDiscount(item);
                if (discount.getAmount().compareTo(BigDecimal.ZERO) >0){
                    discountLines.add(discount);
                }
            }
        }
        return discountLines;
    }

    public Receipt calculateReceipt(CheckoutRequest request){
        BigDecimal subTotal = calculateSubTotal(request);
        List<DiscountLine> discounts = calculateDiscounts(request);
        BigDecimal totalDiscount = BigDecimal.ZERO;
        for (DiscountLine discount : discounts){
            totalDiscount = totalDiscount.add(discount.getAmount());
        }
        BigDecimal total = subTotal.subtract(totalDiscount);
        return new Receipt(
                createReceiptItems(request),
                subTotal,
                discounts,
                totalDiscount,
                total
                );
    }

    private List<ReceiptItem> createReceiptItems(CheckoutRequest request) {
        List<ReceiptItem> receiptItems = new ArrayList<>();
        for (BasketItem item : request.getItems()){
            BigDecimal unitPrice = priceCatalog.getPrice(item.getItemType());
            BigDecimal lineTotal = unitPrice.multiply(BigDecimal.valueOf(item.getQuantity()));
            ReceiptItem receiptItem = new ReceiptItem(
                    item.getItemType(),
                    item.getQuantity(),
                    unitPrice,
                    lineTotal
            );
            receiptItems.add(receiptItem);
        }
        return receiptItems;
    }

}
