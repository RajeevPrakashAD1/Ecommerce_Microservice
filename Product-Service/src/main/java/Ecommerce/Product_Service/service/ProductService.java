package Ecommerce.Product_Service.service;

import Ecommerce.Product_Service.dto.ProductRequestDto;
import Ecommerce.Product_Service.dto.ProductResponseDto;

import java.util.List;

public interface ProductService {

    ProductResponseDto createProduct(ProductRequestDto productRequestDto);
    ProductResponseDto getProductById(String productId);
    ProductResponseDto updateProduct(String productId, ProductRequestDto productRequestDto);
    void deleteProduct(String productId);
    List<ProductResponseDto> getAllProducts();
    void updateProductQuantity(String productId, int quantity);

}
