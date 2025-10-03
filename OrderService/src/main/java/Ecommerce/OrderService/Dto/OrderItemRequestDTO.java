package Ecommerce.OrderService.Dto;


import lombok.Data;

@Data
public class OrderItemRequestDTO {

  private String productId;
  private int quantity;


}
