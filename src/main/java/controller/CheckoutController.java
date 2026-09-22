package controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import model.CheckoutRequest;
import model.Receipt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.CheckoutService;
@Slf4j
@RestController
@RequestMapping("/checkout")
public class CheckoutController {
    private final CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/receipt")
    public String checkoutV2(@Valid @RequestBody CheckoutRequest request){
        log.info("Received checkout API request in v2");
        Receipt receipt = checkoutService.calculateReceipt(request);
        return receipt.toString();
    }
}
