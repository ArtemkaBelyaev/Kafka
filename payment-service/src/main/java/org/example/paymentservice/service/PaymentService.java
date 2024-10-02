package org.example.paymentservice.service;

import lombok.extern.slf4j.Slf4j;
import org.example.paymentservice.model.Order;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class PaymentService {

    private final KafkaTemplate<String, Order> kafkaTemplate;

    public PaymentService(KafkaTemplate<String, Order> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Retryable(value = Exception.class, maxAttempts = 5, backoff = @Backoff(delay = 2000))
    @KafkaListener(topics = "new_orders", groupId = "payment-group")
    public void processPayment(Order order) {
        log.info("Received new order for payment: {}", order.getOrderId());
        try {
            // Обработка платежа
            order.setStatus("PAID");
            kafkaTemplate.send("payed_orders", order);
            log.info("Successfully processed payment for order: {}", order.getOrderId());
        } catch (Exception e) {
            log.error("Failed to process payment for order: {}", order.getOrderId(), e);
            throw new RuntimeException("Error processing payment for order: " + order.getOrderId(), e);
        }
    }
}
