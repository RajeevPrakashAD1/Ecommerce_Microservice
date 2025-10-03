package Ecommerce.Product_Service.service.imp;

import Ecommerce.Product_Service.dto.CategoryExtendedResponse;
import Ecommerce.Product_Service.dto.CategoryRequestDto;
import Ecommerce.Product_Service.dto.CategoryResponseDto;
import Ecommerce.Product_Service.dto.ProductResponseDto;
import Ecommerce.Product_Service.entity.Category;
import Ecommerce.Product_Service.repository.CategoryRepository;
import Ecommerce.Product_Service.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.hibernate.internal.util.collections.ArrayHelper.forEach;

@Service
@RequiredArgsConstructor
public class CategoryServiceImp implements CategoryService {

    private final CategoryRepository categoryRepository; // Assuming you have a repository for Category

    // Implement the methods defined in CategoryService interface
    @Override
    public CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto) {
        // Logic to create a new category
        Logger.getLogger(CategoryServiceImp.class.getName()).info("Creating category: " + categoryRequestDto.getCategoryName());
        Category category = convertToEntity(categoryRequestDto);
        // Save the category entity to the database (assuming you have a repository)
         Category savedCategory = categoryRepository.save(category);

        return convertToResponseDto(category); // Replace with actual saved category


    }

    @Override
    public CategoryResponseDto getCategoryById(String categoryId) {
        // Logic to retrieve a category by its ID
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return convertToResponseDto(category);
    }

    @Override
    public CategoryResponseDto updateCategory(String categoryId, CategoryRequestDto categoryRequestDto) {
        // Logic to update an existing category
        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // Update the existing category with new values
        existingCategory.setCategoryName(categoryRequestDto.getCategoryName());
        existingCategory.setCategoryDescription(categoryRequestDto.getCategoryDescription());

        // Save the updated category entity to the database
        Category updatedCategory = categoryRepository.save(existingCategory);

        return convertToResponseDto(updatedCategory);
    }

    @Override
    public void deleteCategory(String categoryId) {
        // Logic to delete a category by its ID
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        categoryRepository.delete(category);
    }

    @Override
    public List<CategoryExtendedResponse> getAllCategories() {
        // Logic to retrieve all categories
        List<Category> categories = categoryRepository.findAll();
        // Convert the list of Category entities to a list of CategoryResponseDto
        List<CategoryExtendedResponse> extendedResponses = new ArrayList<>();
        for(Category category: categories) {
            extendedResponses.add(convertToExtendedResponse(category));
        }

        return extendedResponses;
    }

    private  CategoryResponseDto convertToResponseDto(Category category) {
        // Logic to convert Category entity to CategoryResponseDto
        return CategoryResponseDto.builder()
                .categoryId(category.getCategoryId())
                .categoryName(category.getCategoryName())
                .categoryDescription(category.getCategoryDescription())
                .build();
    }
    private Category convertToEntity(CategoryRequestDto categoryRequestDto) {
        // Logic to convert CategoryRequestDto to Category entity
        return Category.builder()
                .categoryName(categoryRequestDto.getCategoryName())
                .categoryDescription(categoryRequestDto.getCategoryDescription())
                .build();

    }

    private CategoryExtendedResponse convertToExtendedResponse(Category category) {
        return CategoryExtendedResponse.builder()
                .categoryId(category.getCategoryId())
                .categoryName(category.getCategoryName())
                .categoryDescription(category.getCategoryDescription())
                .products(
                        category.getProducts().stream()
                                .map(product -> ProductResponseDto.builder()
                                        .productId(product.getProductId())
                                        .productName(product.getProductName())
                                        .productDescription(product.getProductDescription())
                                        .productImageUrl(product.getProductImageUrl())
                                        .productPrice(product.getProductPrice())
                                        .productQuantity(product.getProductQuantity())
                                        .inStock(product.isInStock())
                                        .categoryId(product.getCategory().getCategoryId())
                                        .build()
                                )
                                .toList()
                )
                .build();
    }



}
