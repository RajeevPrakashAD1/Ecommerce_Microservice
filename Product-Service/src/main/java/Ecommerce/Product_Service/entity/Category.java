package Ecommerce.Product_Service.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Category {
    @Id
    private String categoryId;
    private String categoryName;
    private String categoryDescription;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products;

    @PrePersist
    public void generateId() {
        this.categoryId = UUID.randomUUID().toString();
    }
}
