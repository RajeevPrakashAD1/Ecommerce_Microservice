package Ecommerce.Product_Service.service.imp;

import Ecommerce.Product_Service.dto.ProductRequestDto;
import Ecommerce.Product_Service.dto.ProductResponseDto;
import Ecommerce.Product_Service.entity.Category;
import Ecommerce.Product_Service.entity.Product;
import Ecommerce.Product_Service.repository.CategoryRepository;
import Ecommerce.Product_Service.repository.ProductRepository;
import Ecommerce.Product_Service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;// Assuming you have a ProductRepository for database operations

    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {

        Category category = categoryRepository.findById(productRequestDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        // Convert ProductRequestDto to Product entity
        Product product = convertToEntity(productRequestDto, category);
        // Save the product entity to the database

        Product savedProduct = productRepository.save(product);
        // Convert the saved Product entity to ProductResponseDto
        return convertToResponseDto(savedProduct);

    }

    @Override
    public ProductResponseDto getProductById(String productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return convertToResponseDto(product);
    }

    @Override
    public ProductResponseDto updateProduct(String productId, ProductRequestDto productRequestDto) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Category category = categoryRepository.findById(productRequestDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // Update the existing product with new values
        existingProduct.setProductName(productRequestDto.getProductName());
        existingProduct.setProductDescription(productRequestDto.getProductDescription());
        existingProduct.setProductImageUrl(productRequestDto.getProductImageUrl());
        existingProduct.setProductPrice(productRequestDto.getProductPrice());
        existingProduct.setProductQuantity(productRequestDto.getProductQuantity());
        existingProduct.setInStock(productRequestDto.isInStock());
        existingProduct.setCategory(category);

        // Save the updated product entity to the database
        Product updatedProduct = productRepository.save(existingProduct);

        return convertToResponseDto(updatedProduct);
    }

    @Override
    public void updateProductQuantity(String productId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        if (product.getProductQuantity() < quantity) {
            return; // Not enough stock
        }
        int currentQuantity = product.getProductQuantity();
        product.setProductQuantity(currentQuantity - quantity);
        productRepository.save(product);
        return;

    }

    @Override
    public void deleteProduct(String productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        productRepository.delete(product);

    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::convertToResponseDto)
                .toList();
    }

    public ProductResponseDto convertToResponseDto(Product product) {
        return ProductResponseDto.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .productDescription(product.getProductDescription())
                .productImageUrl(product.getProductImageUrl())
                .productPrice(product.getProductPrice())
                .productQuantity(product.getProductQuantity())
                .inStock(product.isInStock())
                .categoryId(product.getCategory().getCategoryId())
                .build();
    }


    private Product convertToEntity(ProductRequestDto dto, Category category) {
        return Product.builder()
                .productId(dto.getProductId())
                .productName(dto.getProductName())
                .productDescription(dto.getProductDescription())
                .productImageUrl(dto.getProductImageUrl())
                .productPrice(dto.getProductPrice())
                .productQuantity(dto.getProductQuantity())
                .inStock(dto.isInStock())
                .category(category)
                .build();
    }
}
