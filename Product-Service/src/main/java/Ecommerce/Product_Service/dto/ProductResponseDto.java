package Ecommerce.Product_Service.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductResponseDto {
    private String productId;
    private String productName;
    private String productDescription;
    private String productImageUrl;
    private double productPrice;
    private int productQuantity;
    private boolean inStock;
    private String categoryId; // Assuming categoryId is a String, adjust as necessary
    private String categoryName; // Added to include category information
}
