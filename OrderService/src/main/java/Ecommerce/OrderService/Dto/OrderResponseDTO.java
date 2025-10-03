package Ecommerce.OrderService.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderResponseDTO {
    private String orderId;
    private String customerId;
    private double totalAmount;
    private OrderStatus status;

    private OrderItemResponseDTO[] items; // Array of items in the order
    private LocalDateTime orderDate;


}
