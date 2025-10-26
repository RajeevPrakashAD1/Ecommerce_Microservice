package Ecommerce.PaymentService.Service;

import Ecommerce.PaymentService.Dto.PaymentRequest;
import Ecommerce.PaymentService.Dto.PaymentResponse;
import Ecommerce.PaymentService.Dto.PaymentStatus;
import Ecommerce.PaymentService.Repository.PaymentRepository;
import Ecommerce.PaymentService.Entity.Payment;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Service
public class PaymentService {
    private PaymentRepository paymentRespostory;
    private OrderClient orderClient;
    public PaymentService(PaymentRepository paymentRepository, OrderClient orderClient) {
        this.paymentRespostory = paymentRepository;
        this.orderClient = orderClient;
    }

    public PaymentResponse processPayment(PaymentRequest paymentRequest) {
        // Simulate payment processing logic

        String paymentId = generatePaymentId();
        Payment payment = new Payment();
        payment.setPaymentId(paymentId);
        payment.setOrderId(paymentRequest.getOrderId());
        payment.setAmount(paymentRequest.getAmount());
        payment.setPaymentDate(LocalDateTime.now());
        payment.setPaymentStatus(PaymentStatus.SUCCESS);
        boolean paymentSuccess = new Random().nextBoolean();
        if(paymentSuccess){
            payment.setPaymentStatus(PaymentStatus.SUCCESS);
            payment.setTransactionId("txn" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
            orderClient.updateOrderStatus(paymentRequest.getOrderId(), "CONFIRMED");
        }else {
            payment.setPaymentStatus(PaymentStatus.FAILED);
            payment.setTransactionId("N/A");
            orderClient.updateOrderStatus(paymentRequest.getOrderId(), "FAILED");
        }
            // Assume payment is always successful for this example
        paymentRespostory.save(payment);

        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setPaymentId(payment.getPaymentId());
        paymentResponse.setOrderId(payment.getOrderId());
        paymentResponse.setAmount(payment.getAmount());
        paymentResponse.setPaymentStatus(payment.getPaymentStatus());
        paymentResponse.setTransactionId(payment.getTransactionId());


        return paymentResponse;

    }

    public PaymentResponse getPaymentDetailsByOrderId(String orderId) {
        Payment payment = paymentRespostory.findByOrderId(orderId);
        if (payment == null) {
            return null; // Or throw an exception if preferred
        }

        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setPaymentId(payment.getPaymentId());
        paymentResponse.setOrderId(payment.getOrderId());
        paymentResponse.setAmount(payment.getAmount());
        paymentResponse.setPaymentStatus(payment.getPaymentStatus());
        paymentResponse.setTransactionId(payment.getTransactionId());

        return paymentResponse;
    }


    private String generatePaymentId() {
        return "pay-" + UUID.randomUUID().toString().substring(0, 8);
    }
}
