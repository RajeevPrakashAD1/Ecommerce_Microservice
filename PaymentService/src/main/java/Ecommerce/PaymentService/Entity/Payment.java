package Ecommerce.PaymentService.Entity;

import Ecommerce.PaymentService.Dto.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter@Setter
public class Payment {
    @Id
    private String paymentId;
    private String orderId;
    private String customerId;
    private String transactionId;
    private double amount;
    private LocalDateTime paymentDate;
    private PaymentStatus paymentStatus;

}
