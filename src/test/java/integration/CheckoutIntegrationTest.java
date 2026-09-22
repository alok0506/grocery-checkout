package integration;

import grocery_checkout_service.GroceryCheckoutServiceApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

        MvcResult result = mockMvc.perform(
                        post("/checkout/receipt")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody)
                )
                .andExpect(status().isOk())
                .andReturn();
        String response = result.getResponse().getContentAsString();
        System.out.println("Hi Alok" + response);
        assertTrue(response.contains("Subtotal:"));

        assertTrue(response.contains("£3.30"));
        assertTrue(response.contains("Total Discount:"));
        assertTrue(response.contains("£0.65"));
        assertTrue(response.contains("£2.65"));
    }

    @Test
    void shouldReturnBadRequestForInvalidQuantity() throws Exception {

        String requestBody = """
                {
                    "items": [
                        {
                            "itemType": "BANANA",
                            "quantity": 0
                        }
                    ]
                }
                """;

        MvcResult result = mockMvc.perform(
                post("/checkout/receipt")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
        ).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(400, status);
    }
}