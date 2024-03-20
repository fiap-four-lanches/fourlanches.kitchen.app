package com.fiap.techchallenge.fourlanches.kitchen.app.adapter.driver.queue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.techchallenge.fourlanches.kitchen.app.application.dto.OrderStatusQueueMessageDTO;
import com.fiap.techchallenge.fourlanches.kitchen.app.domain.entity.ProductionOrder;
import com.fiap.techchallenge.fourlanches.kitchen.app.domain.repository.ProductionStatusNotifier;
import com.fiap.techchallenge.fourlanches.kitchen.app.domain.usecase.KitchenUseCase;
import com.fiap.techchallenge.fourlanches.kitchen.app.domain.valueobject.OrderStatus;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.fiap.techchallenge.fourlanches.kitchen.app.adapter.driver.queue.QueueConfiguration.X_REQUEST_ID;

@Component
public class OrderStatusProducer implements ProductionStatusNotifier {

    private final String queueInPreparationName;
    private final String queueFinishedName;
    private final AmqpTemplate queueSender;
    private final KitchenUseCase kitchenUseCase;

    public OrderStatusProducer(@Value("${queue.in-preparation.name}") String queueInPreparationName,
                               @Value("${queue.finished.name}") String queueFinishedName,
                               AmqpTemplate queueSender,
                               KitchenUseCase kitchenUseCase) {
        this.queueInPreparationName = queueInPreparationName;
        this.queueFinishedName = queueFinishedName;
        this.queueSender = queueSender;
        this.kitchenUseCase = kitchenUseCase;
    }

    @Override
    public void notifyOrderInPreparation(Long orderId) throws JsonProcessingException {
        generateOrderStatusChange(orderId, OrderStatus.IN_PREPARATION);
    }

    @Override
    public void notifyOrderFinished(Long orderId) throws JsonProcessingException {
        generateOrderStatusChange(orderId, OrderStatus.READY);
    }

    public void generateOrderStatusChange(Long orderId, OrderStatus status) throws JsonProcessingException {
        ProductionOrder productionOrder = kitchenUseCase.getProductionOrderByOrderId(orderId);

        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeaders(Map.of(X_REQUEST_ID, productionOrder.getOriginalRequestId()));
        var orderStatusMessage = OrderStatusQueueMessageDTO.fromOrder(orderId, status);

        String jsonMessage = new ObjectMapper().writeValueAsString(orderStatusMessage);
        var message = new Message(jsonMessage.getBytes(), messageProperties);

        var queueName = "";

        switch (status) {
            case IN_PREPARATION -> queueName = queueInPreparationName;
            case READY -> queueName = queueFinishedName;
        }

        queueSender.convertAndSend(queueName, message);
    }
}