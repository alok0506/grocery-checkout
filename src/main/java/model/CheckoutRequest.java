package model;

import java.util.List;

public class CheckoutRequest {
    private List<BasketItem> item;
    public CheckoutRequest(){
    }

    public CheckoutRequest(List<BasketItem> item) {
        this.item = item;
    }

    public List<BasketItem> getItem() {
        return item;
    }

    public void setItem(List<BasketItem> item) {
        this.item = item;
    }
}
