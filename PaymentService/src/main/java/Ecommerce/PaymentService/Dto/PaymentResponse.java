package Ecommerce.PaymentService.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class PaymentResponse {
    private String paymentId;
    private String orderId;
    private double amount;
    private String customerId;
    private String currency;
    private PaymentStatus status;
    private String paymentMethod;
    private String transactionId;
    private String timestamp;
    private PaymentStatus paymentStatus;
}
