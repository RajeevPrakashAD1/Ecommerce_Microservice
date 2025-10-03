package Ecommerce.OrderService.Controller;

import Ecommerce.OrderService.Dto.OrderRequestDTO;
import Ecommerce.OrderService.Dto.OrderResponseDTO;
import Ecommerce.OrderService.Dto.OrderStatus;
import Ecommerce.OrderService.Service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        OrderResponseDTO orderResponseDTO =  orderService.createOrder(orderRequestDTO);
        return ResponseEntity.ok(orderResponseDTO);

    }
    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrderById(@PathVariable String orderId) {
        OrderResponseDTO orderResponseDTO = orderService.getOrderById(orderId);
        return ResponseEntity.ok(orderResponseDTO);
    }

    @PatchMapping("/{orderId}/status")
    public ResponseEntity<?> updateOrderStatus(@PathVariable String orderId, @RequestParam OrderStatus status) {
        orderService.updateOrderStatus(orderId, status);
        return ResponseEntity.ok("successfully updated order status");
    }
}
