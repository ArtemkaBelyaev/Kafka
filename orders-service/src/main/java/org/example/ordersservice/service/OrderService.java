package org.example.ordersservice.service;

import org.example.ordersservice.model.Order;
import org.example.ordersservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {
    private static final String NEW_ORDERS_TOPIC = "new_orders";
    private final KafkaTemplate<String, Order> kafkaTemplate;

    private final OrderRepository orderRepository;

    public OrderService(KafkaTemplate<String, Order> kafkaTemplate, OrderRepository orderRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.orderRepository = orderRepository;
    }

    public Order createOrder(Order order) {
        order.setOrderId(UUID.randomUUID());
        orderRepository.save(order);
        kafkaTemplate.send(NEW_ORDERS_TOPIC, order);
        return order;
    }

    public Order updateOrderStatus(UUID orderId, String status) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        order.setStatus(status);
        orderRepository.save(order);
        kafkaTemplate.send(NEW_ORDERS_TOPIC, order);
        return order;
    }
}
