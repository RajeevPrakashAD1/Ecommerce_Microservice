package Ecommerce.Product_Service.controller;

import Ecommerce.Product_Service.dto.ProductRequestDto;
import Ecommerce.Product_Service.dto.ProductResponseDto;
import Ecommerce.Product_Service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto productRequestDto) {
        // Logic to create a product
        return productService.createProduct(productRequestDto);

    }

    @GetMapping("/{productId}")
    public ProductResponseDto getProductById(@PathVariable String productId) {
        // Logic to get a product by ID
        return productService.getProductById(productId);
    }
    @GetMapping
    public List<ProductResponseDto> getAllProducts() {
        // Logic to get all products
        return productService.getAllProducts();
    }
    @PutMapping("/stock/{productId}/quantity/{quantity}")
    public void updateProductQuantity(@PathVariable String productId, @PathVariable int quantity) {
        // Logic to update product quantity
        productService.updateProductQuantity(productId, quantity);
    }

}
