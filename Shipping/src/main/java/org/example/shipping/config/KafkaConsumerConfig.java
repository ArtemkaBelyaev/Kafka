package org.example.shipping.config;

import org.example.shipping.model.Order;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    private final KafkaErrorHandler kafkaErrorHandler;

    public KafkaConsumerConfig(KafkaErrorHandler kafkaErrorHandler) {
        this.kafkaErrorHandler = kafkaErrorHandler;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Order> kafkaListenerContainerFactory(ConsumerFactory<String, Order> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, Order> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);

        factory.setConcurrency(5);

        factory.setCommonErrorHandler(kafkaErrorHandler.errorHandler());

        return factory;
    }
}