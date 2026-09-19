package service;

import model.ItemType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

//Central place containing all the prices
@Component
public class PriceCatalog {
    private final Map<ItemType, BigDecimal> prices = Map.of(
            ItemType.BANANA,new BigDecimal("0.50"),
            ItemType.ORANGE,new BigDecimal("0.30"),
            ItemType.APPLE,new BigDecimal("0.60"),
            ItemType.LEMON,new BigDecimal("0.25"),
            ItemType.PEACH,new BigDecimal("0.75")
    );

    //Function which returns all the prices based on the items which we want
    public BigDecimal getPrice(ItemType itemType){
        return prices.get(itemType);
    }
}
