package Ecommerce.OrderService.Dto;

import lombok.Data;


@Data
public class OrderRequestDTO {
    private String customerId;
    private OrderItemRequestDTO[] items; // Array of items in the order

}
