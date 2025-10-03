package Ecommerce.OrderService.Entity;

import Ecommerce.OrderService.Dto.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    private String orderId;
    private String customerId;
    private double totalPrice;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus; // This can be an enum for better type safety

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>(); // List of items in the order
    private LocalDateTime orderDate;


    // Additional fields can be added as needed

    // You can also add methods for business logic if necessary

}
