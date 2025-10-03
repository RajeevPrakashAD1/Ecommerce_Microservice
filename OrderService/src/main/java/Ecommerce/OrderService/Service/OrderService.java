package Ecommerce.OrderService.Service;

import Ecommerce.OrderService.Dto.*;
import Ecommerce.OrderService.Entity.Order;
import Ecommerce.OrderService.Entity.OrderItem;
import Ecommerce.OrderService.Respository.OrderItemRepository;
import Ecommerce.OrderService.Respository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductClient productClient;

    // ✅ removed direct dependency on OrderItemRepository because we rely on cascade now
    public OrderService(OrderRepository orderRepository,
                        ProductClient productClient) {
        this.orderRepository = orderRepository;
        this.productClient = productClient;
    }

    public OrderResponseDTO createOrder(OrderRequestDTO requestDTO) {

        // Create a new order
        Order order = new Order();
        order.setOrderId(UUID.randomUUID().toString());
        order.setCustomerId(requestDTO.getCustomerId());
        order.setOrderStatus(OrderStatus.PENDING);
        order.setOrderDate(LocalDateTime.now());

        double totalAmount = 0.0;
        List<OrderItem> items = new ArrayList<>();

        // Validate products and build items
        for (OrderItemRequestDTO itemReq : requestDTO.getItems()) {
            ProductResponseDTO product = productClient.getProductById(itemReq.getProductId());
            if (product == null) {
                throw new RuntimeException("Product not found: " + itemReq.getProductId());
            }
            if (product.getProductQuantity() < itemReq.getQuantity()) {
                throw new RuntimeException("Insufficient quantity for product: " + itemReq.getProductId());
            }

            // Deduct product quantity
            productClient.updateProductQuantity(
                    itemReq.getProductId(),
                    product.getProductQuantity() - itemReq.getQuantity()
            );

            double totalPrice = itemReq.getQuantity() * product.getProductPrice();
            totalAmount += totalPrice;

            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(itemReq.getProductId());
            orderItem.setQuantity(itemReq.getQuantity());
            orderItem.setPrice(product.getProductPrice());
            orderItem.setOrder(order);

            items.add(orderItem);
        }

        order.setTotalPrice(totalAmount);
        order.setItems(items);                   // ✅ add items to order

        // ✅ save order; cascading saves items
        Order savedOrder = orderRepository.save(order);

        // Build DTO response
        List<OrderItemResponseDTO> itemResponseDTOS = new ArrayList<>();
        for (OrderItem item : savedOrder.getItems()) {
            itemResponseDTOS.add(new OrderItemResponseDTO(
                    item.getOrderItemId(),
                    item.getProductId(),
                    item.getQuantity(),
                    item.getPrice()
            ));
        }

        return new OrderResponseDTO(
                savedOrder.getOrderId(),
                savedOrder.getCustomerId(),
                savedOrder.getTotalPrice(),
                savedOrder.getOrderStatus(),
                itemResponseDTOS.toArray(new OrderItemResponseDTO[0]),
                savedOrder.getOrderDate()
        );
    }

    public OrderResponseDTO getOrderById(String orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        List<OrderItemResponseDTO> items = new ArrayList<>();
        for (OrderItem item : order.getItems()) {  // ✅ use order.getItems()
            items.add(new OrderItemResponseDTO(
                    item.getOrderItemId(),
                    item.getProductId(),
                    item.getQuantity(),
                    item.getPrice()
            ));
        }

        return new OrderResponseDTO(
                order.getOrderId(),
                order.getCustomerId(),
                order.getTotalPrice(),
                order.getOrderStatus(),
                items.toArray(new OrderItemResponseDTO[0]),
                order.getOrderDate()
        );
    }

    public List<OrderResponseDTO> getAllOrders() {
        List<OrderResponseDTO> responses = new ArrayList<>();
        for (Order order : orderRepository.findAll()) {
            List<OrderItemResponseDTO> items = new ArrayList<>();
            for (OrderItem item : order.getItems()) {
                items.add(new OrderItemResponseDTO(
                        item.getOrderItemId(),
                        item.getProductId(),
                        item.getQuantity(),
                        item.getPrice()
                ));
            }
            responses.add(new OrderResponseDTO(
                    order.getOrderId(),
                    order.getCustomerId(),
                    order.getTotalPrice(),
                    order.getOrderStatus(),
                    items.toArray(new OrderItemResponseDTO[0]),
                    order.getOrderDate()
            ));
        }
        return responses;
    }

    public List<OrderResponseDTO> getOrdersByCustomerId(String customerId) {
        List<OrderResponseDTO> responses = new ArrayList<>();
        for (Order order : orderRepository.findByCustomerId(customerId)) {
            List<OrderItemResponseDTO> items = new ArrayList<>();
            for (OrderItem item : order.getItems()) {
                items.add(new OrderItemResponseDTO(
                        item.getOrderItemId(),
                        item.getProductId(),
                        item.getQuantity(),
                        item.getPrice()
                ));
            }
            responses.add(new OrderResponseDTO(
                    order.getOrderId(),
                    order.getCustomerId(),
                    order.getTotalPrice(),
                    order.getOrderStatus(),
                    items.toArray(new OrderItemResponseDTO[0]),
                    order.getOrderDate()
            ));
        }
        return responses;
    }

    public void updateOrderStatus(String orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setOrderStatus(status);
        orderRepository.save(order);
    }
}
