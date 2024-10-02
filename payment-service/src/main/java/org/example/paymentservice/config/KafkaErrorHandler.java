package org.example.paymentservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.stereotype.Service;

@Service
public class KafkaErrorHandler {

    @Bean
    public DefaultErrorHandler errorHandler() {
        DefaultErrorHandler errorHandler = new DefaultErrorHandler((record, exception) -> {
            System.err.println("Error handling message: " + record.value() + " due to " + exception.getMessage());
        });
        return errorHandler;
    }
}