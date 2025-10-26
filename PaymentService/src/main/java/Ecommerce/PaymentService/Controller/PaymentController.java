package Ecommerce.PaymentService.Controller;

import Ecommerce.PaymentService.Dto.PaymentRequest;
import Ecommerce.PaymentService.Dto.PaymentResponse;
import Ecommerce.PaymentService.Service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/payments")
public class PaymentController {
    private PaymentService paymentService;
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    @GetMapping
    public String hello(){
        return "Hello from payment service";
    }
    @PostMapping
    public ResponseEntity<PaymentResponse> processPayment(PaymentRequest paymentRequest){
        PaymentResponse res =  paymentService.processPayment(paymentRequest);
        return ResponseEntity.ok(res);

    }
    @GetMapping("/order/{orderId}")
    public ResponseEntity<PaymentResponse> getPaymentDetailsByOrderId(String orderId){
        PaymentResponse res = paymentService.getPaymentDetailsByOrderId(orderId);
        return ResponseEntity.ok(res);
    }
}
