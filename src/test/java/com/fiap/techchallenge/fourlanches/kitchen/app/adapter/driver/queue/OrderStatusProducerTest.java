package com.fiap.techchallenge.fourlanches.kitchen.app.adapter.driver.queue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fiap.techchallenge.fourlanches.kitchen.app.domain.entity.ProductionOrder;
import com.fiap.techchallenge.fourlanches.kitchen.app.domain.entity.ProductionOrderStatus;
import com.fiap.techchallenge.fourlanches.kitchen.app.domain.usecase.KitchenUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;

import static com.fiap.techchallenge.fourlanches.kitchen.app.adapter.driver.queue.QueueConfiguration.X_REQUEST_ID;
import static com.fiap.techchallenge.fourlanches.kitchen.app.domain.entity.ProductionOrderStatus.FINISHED;
import static com.fiap.techchallenge.fourlanches.kitchen.app.domain.entity.ProductionOrderStatus.IN_PREPARATION;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class OrderStatusProducerTest {

    private static final String QUEUE_ORDER_STATUS_NAME = "order_status_queue";
    private static final Long DEFAULT_ORDER_ID = 2L;
    private static final String ORIGINAL_REQUEST_ID = "test-req-id";
    @Mock
    private AmqpTemplate queueSender;
    @Mock
    private KitchenUseCase kitchenUseCase;

    private OrderStatusProducer orderStatusProducer;

    @Captor
    private ArgumentCaptor<Message> messageCaptor;

    @BeforeEach
    public void setUp() {
        orderStatusProducer = new OrderStatusProducer(QUEUE_ORDER_STATUS_NAME, QUEUE_ORDER_STATUS_NAME, queueSender, kitchenUseCase);
        when(kitchenUseCase.getProductionOrderByOrderId(eq(DEFAULT_ORDER_ID))).thenReturn(ProductionOrder.builder()
                        .orderItems(Collections.emptyList())
                        .originalRequestId(ORIGINAL_REQUEST_ID)
                .build());

    }

    @Test
    void givenOrderIsInPreparationThenShouldSendOrderToOrderStatusQueueWithStatusInPreparation() throws JsonProcessingException {
        // Act
        orderStatusProducer.notifyOrderInPreparation(DEFAULT_ORDER_ID);

        // Assert
        verify(queueSender).convertAndSend(eq(QUEUE_ORDER_STATUS_NAME), messageCaptor.capture());
        assertMessageContainsFieldsAndHeader(IN_PREPARATION);
    }

    @Test
    void givenOrderIsFinishedThenShouldSendOrderToOrderStatusQueueWithStatusFinished() throws JsonProcessingException {
        // Act
        orderStatusProducer.notifyOrderFinished(DEFAULT_ORDER_ID);

        // Assert
        verify(queueSender).convertAndSend(eq(QUEUE_ORDER_STATUS_NAME), messageCaptor.capture());
        assertMessageContainsFieldsAndHeader(FINISHED);
    }

    private void assertMessageContainsFieldsAndHeader(ProductionOrderStatus finished) {
        Message message = messageCaptor.getValue();
        String messageBody = new String(message.getBody());
        assertThat(messageBody).contains("kitchen");
        assertThat(messageBody).contains(String.valueOf(DEFAULT_ORDER_ID));
        assertThat(messageBody).contains(finished.toString());
        assertThat(message.getMessageProperties().getHeader(X_REQUEST_ID).toString()).contains(ORIGINAL_REQUEST_ID);
    }
}