package service;

import discount.DiscountStrategy;
import exception.InvalidCheckoutException;
import lombok.extern.slf4j.Slf4j;
import model.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import java.math.BigDecimal;
@Slf4j
@Service
public class CheckoutService {
    private final PriceCatalog priceCatalog;
    private final List<DiscountStrategy> discountStrategies;

    public CheckoutService(PriceCatalog priceCatalog, List<DiscountStrategy> discountStrategies) {
        this.priceCatalog = priceCatalog;
        this.discountStrategies = discountStrategies;
    }
    //Main function called from controller class to create receipt of final basket value
    public Receipt calculateReceipt(CheckoutRequest request){
        if(request == null || request.getItems() == null || request.getItems().isEmpty()){
            throw new InvalidCheckoutException("Checkout must contain at least one Item");
        }
        log.info("Checkout request received with {} items", request.getItems().size());
        BigDecimal subTotal = calculateSubTotal(request);
        List<DiscountLine> discounts = calculateDiscounts(request);
        BigDecimal totalDiscount = BigDecimal.ZERO;
        for (DiscountLine discount : discounts){
            totalDiscount = totalDiscount.add(discount.getAmount());
        }
        BigDecimal total = subTotal.subtract(totalDiscount);
        log.info("Checkout completed successfully. SubTotal: {}, Discount: {},Total: {}",subTotal,totalDiscount, total);
        return new Receipt(
                createReceiptItems(request),
                subTotal,
                discounts,
                totalDiscount,
                total
        );
    }
    //Calculating first price value of all the products without discount
    private BigDecimal calculateSubTotal(CheckoutRequest checkoutRequest){
        BigDecimal subTotal = BigDecimal.ZERO;
        for(BasketItem item:checkoutRequest.getItems()){
            log.debug("Calculating price for item: {}, quantity: {}",item.getItemType(),item.getQuantity());
            ItemType itemType = item.getItemType();
            int quantity = item.getQuantity();

            BigDecimal unitPrice = priceCatalog.getPrice(itemType);
            BigDecimal total = unitPrice.multiply(BigDecimal.valueOf(quantity));
            subTotal = subTotal.add(total);
        }
        return subTotal;
    }
    //Calculating discount based on all the discounts available on different products
    private List<DiscountLine> calculateDiscounts(CheckoutRequest request){
        List<DiscountLine> discountLines = new ArrayList<>();
        for (BasketItem item : request.getItems()){
            for (DiscountStrategy strategy : discountStrategies){
                DiscountLine discount = strategy.calculateDiscount(item);
                if (discount.getAmount().compareTo(BigDecimal.ZERO) >0){
                    log.debug("Discount applied: {} - Amount: {}", discount.getDescription(), discount.getAmount());
                    discountLines.add(discount);
                }
            }
        }
        return discountLines;
    }

    private List<ReceiptItem> createReceiptItems(CheckoutRequest request) {
        List<ReceiptItem> receiptItems = new ArrayList<>();
        for (BasketItem item : request.getItems()){
            log.debug("Creating receipt item for: {}, quantity: {}", item.getItemType(), item.getQuantity());
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
