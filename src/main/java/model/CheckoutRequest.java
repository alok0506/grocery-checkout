package model;

import java.util.List;

public class CheckoutRequest {
    private List<BasketItem> items;
    public CheckoutRequest(){
    }

    public CheckoutRequest(List<BasketItem> items) {
        this.items = items;
    }

    public List<BasketItem> getItems() {
        return items;
    }

    public void setItem(List<BasketItem> items) {
        this.items = items;
    }
}
