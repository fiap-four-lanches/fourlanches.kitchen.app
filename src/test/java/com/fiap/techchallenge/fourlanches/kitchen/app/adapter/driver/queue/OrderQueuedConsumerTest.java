package com.fiap.techchallenge.fourlanches.kitchen.app.adapter.driver.queue;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.techchallenge.fourlanches.kitchen.app.domain.entity.ProductionOrderCategory;
import com.fiap.techchallenge.fourlanches.kitchen.app.domain.usecase.KitchenUseCase;
import com.fiap.techchallenge.fourlanches.kitchen.app.domain.valueobject.ProductionOrderIntent;
import com.fiap.techchallenge.fourlanches.kitchen.app.domain.valueobject.ProductionOrderIntentItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.verify;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class OrderQueuedConsumerTest {

    @Mock
    private KitchenUseCase kitchenUseCase;

    @InjectMocks
    private OrderQueuedConsumer consumer;

    @Test
    void givenProductionOrderIntentMessageOkThenShouldAddOrderToProductionQueue() throws JacksonException {
        // Arrange
        var productionOrderIntent = ProductionOrderIntent.builder()
                .orderItems(List.of(ProductionOrderIntentItem.builder()
                                .name("")
                                .category(ProductionOrderCategory.DRINK.toString())
                                .description("description")
                                .quantity(1).build()))
                .build();
        String requestId = "testRequestID";
        var json = new ObjectMapper().writeValueAsString(productionOrderIntent);

        // Act
        consumer.receiveProductionOrderIntentMessage(json, requestId);

        // Assert
        productionOrderIntent.setOriginalRequestId(requestId);
        verify(kitchenUseCase).addOrderToProductionQueue(productionOrderIntent);
    }

    @Test
    void givenProductionOrderIntentMessageInvalidThenShouldThrowJacksonException() {
        // Arrange
        String invalidMessage = "{\"Amount\": 1000Currency\": \"USD\"}";

        // Assert
        assertThatExceptionOfType(JacksonException.class).isThrownBy(() -> {
            // Act
            consumer.receiveProductionOrderIntentMessage(invalidMessage, "testRequestID");
        });
    }
}