package Ecommerce.Product_Service.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class CategoryRequestDto {

    private String categoryId; // Optional, if you want to allow updating an existing category
    private String categoryName;
    private String categoryDescription; // Optional, if you want to include a description
    private String categoryImageUrl; // Optional, if you want to include an image URL for the category
}
