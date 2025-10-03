package Ecommerce.OrderService.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderItemResponseDTO {
        private String orderItemId;
        private String productId;
        private int quantity;
        private double price; // Price per unit

}
