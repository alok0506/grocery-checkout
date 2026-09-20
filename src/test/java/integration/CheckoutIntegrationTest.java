package integration;

import grocery_checkout_service.GroceryCheckoutServiceApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = GroceryCheckoutServiceApplication.class)
@AutoConfigureMockMvc
class CheckoutIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCalculateCheckoutSuccessfully() throws Exception {
        String requestBody = """
                {
                    "items": [
                        {
                            "itemType": "BANANA",
                            "quantity": 3
                        },
                        {
                            "itemType": "ORANGE",
                            "quantity": 4
                        },
                        {
                            "itemType": "APPLE",
                            "quantity": 1
                        }
                    ]
                }
                """;

        mockMvc.perform(
                        post("/checkout")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.subTotal").value(3.30))
                .andExpect(jsonPath("$.totalDiscount").value(0.65))
                .andExpect(jsonPath("$.total").value(2.65));
    }
}