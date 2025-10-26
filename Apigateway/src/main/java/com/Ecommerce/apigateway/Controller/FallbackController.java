package com.Ecommerce.apigateway.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @GetMapping("/fallback/products")
    public ResponseEntity<String> productFallback() {
        return ResponseEntity.ok("⚠️ Product Service is unavailable. Please try again later.");
    }

    @GetMapping("/fallback/orders")
    public ResponseEntity<String> orderFallback() {
        return ResponseEntity.ok("⚠️ Order Service is unavailable. Please try again later.");
    }

    @GetMapping("/fallback/payments")
    public ResponseEntity<String> paymentFallback() {
        return ResponseEntity.ok("⚠️ Payment Service is unavailable. Please try again later.");
    }
}
