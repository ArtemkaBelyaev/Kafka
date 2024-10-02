package org.example.notifications.service;

import lombok.extern.slf4j.Slf4j;
import org.example.notifications.model.Order;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class NotificationService {

    @KafkaListener(topics = "sent_orders", groupId = "notifications-group")
    public void sendNotification(Order order) {
        log.info("Notification sent for Order ID: {}, with status: {}", order.getOrderId(), order.getStatus());
        System.out.println("Notification sent for Order ID: " + order.getOrderId() + ", Status: " + order.getStatus());
    }
}