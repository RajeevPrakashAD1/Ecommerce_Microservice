package Ecommerce.PaymentService.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PaymentRequest {
    private String orderId;
    private double amount;
    private String customerId;
    private String paymentMethod; // e.g., CREDIT_CARD, PAYPAL, etc.

}
