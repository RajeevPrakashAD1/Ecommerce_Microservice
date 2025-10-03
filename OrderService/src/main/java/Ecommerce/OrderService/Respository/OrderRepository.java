package Ecommerce.OrderService.Respository;

import Ecommerce.OrderService.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {


    List<Order> findByCustomerId(String customerId);
}
