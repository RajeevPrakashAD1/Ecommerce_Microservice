package Ecommerce.Product_Service.controller;

import Ecommerce.Product_Service.dto.CategoryExtendedResponse;
import Ecommerce.Product_Service.dto.CategoryRequestDto;
import Ecommerce.Product_Service.dto.CategoryResponseDto;
import Ecommerce.Product_Service.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @PostMapping
    public CategoryResponseDto createCategory(@RequestBody  CategoryRequestDto categoryRequestDto) {
        return categoryService.createCategory(categoryRequestDto);

    }
    @GetMapping
    public List<CategoryExtendedResponse> getAllCategories() {
        return categoryService.getAllCategories();
    }
    @GetMapping("/{categoryId}")
    public CategoryResponseDto getCategoryById(@PathVariable String categoryId) {
        return categoryService.getCategoryById(categoryId);
    }


}
