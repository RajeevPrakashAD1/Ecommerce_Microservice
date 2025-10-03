package Ecommerce.Product_Service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Product {

    @Id
    private String productId;
    private String productName;
    private String productDescription;
    private String productImageUrl;
    private double productPrice;
    private int productQuantity;
    private boolean inStock;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private  Category category;

    @PrePersist
    private void onCreate() {
        if (this.productId == null) {
            this.productId = UUID.randomUUID().toString();
        }

        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    private void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }


    // Additional fields can be added as needed

}
