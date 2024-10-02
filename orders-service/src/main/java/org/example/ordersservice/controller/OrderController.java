package org.example.ordersservice.controller;

import org.example.ordersservice.model.Order;
import org.example.ordersservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @PutMapping("/{orderId}")
    public Order updateOrderStatus(@PathVariable UUID orderId, @RequestParam String status) {
        return orderService.updateOrderStatus(orderId, status);
    }
}
