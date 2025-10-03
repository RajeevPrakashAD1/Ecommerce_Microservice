package Ecommerce.Product_Service.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class CategoryResponseDto {
    private String categoryId;
    private String categoryName;
    private String categoryDescription; // Optional, if you want to include a description
    private String categoryImageUrl; // Optional, if you want to include an image URL for the category
}
