package model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class CheckoutRequest {
    @NotEmpty(message = "Items cannot be empty")
    private List<@Valid BasketItem> items;
    public CheckoutRequest(){
    }

    public CheckoutRequest(List<BasketItem> items) {
        this.items = items;
    }

    public List<BasketItem> getItems() {
        return items;
    }

    public void setItems(List<BasketItem> items) {
        this.items = items;
    }
}
