package org.example.shipping.service;

import lombok.extern.slf4j.Slf4j;
import org.example.shipping.model.Order;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ShippingService {

    private final KafkaTemplate<String, Order> kafkaTemplate;

    public ShippingService(KafkaTemplate<String, Order> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "payed_orders", groupId = "shipping-group")
    public void shipOrder(Order order) {
        log.info("Received new order for payment: {}", order.getOrderId());
        try {
            order.setStatus("SHIPPED");
            kafkaTemplate.send("sent_orders", order);
            log.info("Successfully processed shipping for order: {}", order.getOrderId());
        } catch (Exception e) {
            log.error("Failed to process shipping for order: {}", order.getOrderId(), e);
            throw new RuntimeException("Error processing shipping for order: " + order.getOrderId(), e);
        }

    }
}