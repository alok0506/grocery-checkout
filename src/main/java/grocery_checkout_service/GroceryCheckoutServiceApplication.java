package grocery_checkout_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
		"controller",
		"service",
		"discount",
		"grocery_checkout_service"
})
public class GroceryCheckoutServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GroceryCheckoutServiceApplication.class, args);
	}

}
