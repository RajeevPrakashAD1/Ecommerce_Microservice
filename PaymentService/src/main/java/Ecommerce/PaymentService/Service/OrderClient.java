package Ecommerce.PaymentService.Service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service

public class OrderClient {
    private final RestTemplate restTemplate;

    public OrderClient(RestTemplate restTemplate){
        this.restTemplate =restTemplate;
    }
    private String baseUrl = "http://order-service/orders";

    public void updateOrderStatus(String orderId, String status){
        String url = baseUrl + "/"+orderId+"/status?status="+status;
        restTemplate.patchForObject(url, null, String.class);
    }


}
