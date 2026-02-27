package com.foodie.order_service.kafka;

import com.foodie.order_service.dto.OrderCreatedEventDTO;
import com.foodie.order_service.entity.OrderEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class OrderProducer {

    private static final Logger logger =
            LoggerFactory.getLogger(OrderProducer.class);


    private final KafkaTemplate<String, OrderCreatedEventDTO> kafkaTemplate;

    private static final String TOPIC_NAME = "order-created-topic";

    public OrderProducer(KafkaTemplate<String,
            OrderCreatedEventDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderCreatedEvent(OrderCreatedEventDTO eventDTO) {
      //  kafkaTemplate.send("order-created", order.getOrderId().toString(), order);

        CompletableFuture<SendResult<String,
                                OrderCreatedEventDTO>> future =
                kafkaTemplate.send(TOPIC_NAME, eventDTO);

        future.whenComplete((result, ex) -> {
            if (ex == null) {

                logger.info("✅ Message sent successfully!");
                logger.info("Topic: {}",
                        result.getRecordMetadata().topic());
                logger.info("Partition: {}",
                        result.getRecordMetadata().partition());
                logger.info("Offset: {}",
                        result.getRecordMetadata().offset());

            } else {
                logger.error("❌ Failed to send message: {}",
                        ex.getMessage());
            }
        });
    }

}
