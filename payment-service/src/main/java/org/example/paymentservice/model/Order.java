package org.example.paymentservice.model;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class Order implements Serializable {

    private UUID orderId;
    private String status;

    public Order(UUID orderId, String status) {
        this.orderId = orderId;
        this.status = status;
    }
}
