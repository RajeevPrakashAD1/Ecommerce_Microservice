package Ecommerce.PaymentService.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class OderUpdateRequestDto {

    private String orderId;
    private String status; // e.g., "SHIPPED", "DELIVERED", "CANCELLED"
}
