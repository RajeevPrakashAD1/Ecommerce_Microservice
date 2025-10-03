package Ecommerce.OrderService.Service;


import Ecommerce.OrderService.Dto.ProductResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductClient {

    private final RestTemplate restTemplate;
    String baseUrl = "http://localhost:6001/products";
    public ProductClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public ProductResponseDTO getProductById(String productId) {
        return restTemplate.getForObject(baseUrl + "/" + productId, ProductResponseDTO.class);
    }
    public void updateProductQuantity(String productId, int quantity) {
        restTemplate.put(baseUrl + "/" + "stock/"+ productId + "/quantity/" + quantity, boolean.class);
    }
}
