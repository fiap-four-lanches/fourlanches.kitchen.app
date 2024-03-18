package com.fiap.techchallenge.fourlanches.kitchen.app.adapter.driver.api.queue;

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

import static com.fiap.techchallenge.fourlanches.kitchen.app.adapter.driver.api.queue.HeaderConstant.X_REQUEST_ID;

@Component
public class OrderStatusProducer implements ProductionStatusNotifier {

    private String queueOrderStatusName;
    private AmqpTemplate queueSender;
    private KitchenUseCase kitchenUseCase;

    public OrderStatusProducer(@Value("${queue.order.status.name}") String queueOrderStatusName, AmqpTemplate queueSender, KitchenUseCase kitchenUseCase) {
        this.queueOrderStatusName = queueOrderStatusName;
        this.queueSender = queueSender;
        this.kitchenUseCase = kitchenUseCase;
    }

    @Override
    public void notifyOrderInPreparation(Long orderId) {
        generateOrderStatusChange(orderId, OrderStatus.IN_PREPARATION);
    }

    @Override
    public void notifyOrderFinished(Long orderId) {
        generateOrderStatusChange(orderId, OrderStatus.FINISHED);
    }

    public void generateOrderStatusChange(Long orderId, OrderStatus status){
        try {
            ProductionOrder productionOrder = kitchenUseCase.getProductionOrderByOrderId(orderId);

            MessageProperties messageProperties = new MessageProperties();
            messageProperties.setHeaders(Map.of(X_REQUEST_ID, productionOrder.getOriginalRequestId()));
            var orderStatusMessage = OrderStatusQueueMessageDTO.fromOrder(orderId, status);

            String paymentMessageJson = new ObjectMapper().writeValueAsString(orderStatusMessage);
            var message = new Message(paymentMessageJson.getBytes(), messageProperties);

            queueSender.convertAndSend(queueOrderStatusName, message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
