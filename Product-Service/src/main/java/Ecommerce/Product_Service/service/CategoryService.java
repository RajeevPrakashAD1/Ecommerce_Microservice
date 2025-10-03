package Ecommerce.Product_Service.service;

import Ecommerce.Product_Service.dto.CategoryExtendedResponse;
import Ecommerce.Product_Service.dto.CategoryRequestDto;
import Ecommerce.Product_Service.dto.CategoryResponseDto;

import java.util.List;

public interface CategoryService {
    // Define methods for category management here
    // For example:
     CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto);
     CategoryResponseDto getCategoryById(String categoryId);
     CategoryResponseDto updateCategory(String categoryId, CategoryRequestDto categoryRequestDto);
     void deleteCategory(String categoryId);
     List<CategoryExtendedResponse> getAllCategories();


}
