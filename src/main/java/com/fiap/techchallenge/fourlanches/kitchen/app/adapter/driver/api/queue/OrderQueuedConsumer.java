package com.fiap.techchallenge.fourlanches.kitchen.app.adapter.driver.api.queue;


import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.techchallenge.fourlanches.kitchen.app.domain.usecase.KitchenUseCase;
import com.fiap.techchallenge.fourlanches.kitchen.app.domain.valueobject.ProductionOrderIntent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static com.fiap.techchallenge.fourlanches.kitchen.app.adapter.driver.api.queue.HeaderConstant.X_REQUEST_ID;

@Slf4j
@Component
@AllArgsConstructor
public class OrderQueuedConsumer {

    @Autowired
    private final KitchenUseCase kitchenUseCase;

    @RabbitListener(queues = "${queue.order.status.name}")
    public void receiveProductionOrderIntentMessage(@Payload String message,
                                                    @Header(X_REQUEST_ID) String requestId) throws JacksonException {
        log.info("received production intent message [message:{}][request_id:{}]", message, requestId);
        var productionOrderIntent = new ObjectMapper().readValue(message, ProductionOrderIntent.class);
        productionOrderIntent.setOriginalRequestId(requestId);
        kitchenUseCase.addOrderToProductionQueue(productionOrderIntent);
        log.info("processed production intent message [message:{}][request_id:{}]", message, requestId);
    }

}
