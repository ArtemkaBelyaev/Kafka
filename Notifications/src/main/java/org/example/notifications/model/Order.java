package org.example.notifications.model;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class Order implements Serializable {
    private UUID orderId;
    private String status;
    //
}