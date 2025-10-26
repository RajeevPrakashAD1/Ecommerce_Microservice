package Ecommerce.PaymentService.Repository;

import Ecommerce.PaymentService.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, String> {
    Payment findByOrderId(String orderId);
}
