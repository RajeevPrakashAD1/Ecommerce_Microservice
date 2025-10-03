package Ecommerce.OrderService.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    @Id
    private String orderItemId; // Unique identifier for the order item
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
    private String productId; // Reference to the product being ordered
    private int quantity; // Quantity of the product ordered
    private double price; // Price of the product at the time of order

    // Additional fields can be added as needed, such as discounts, taxes, etc.
    @PrePersist
    private void onCreate() {
        if (this.orderItemId == null) {
            this.orderItemId = UUID.randomUUID().toString();
        }

    }
}
