package com.fiap.techchallenge.fourlanches.kitchen.app.application.usecase;

import com.fiap.techchallenge.fourlanches.kitchen.app.domain.entity.ProductionOrder;
import com.fiap.techchallenge.fourlanches.kitchen.app.domain.entity.ProductionOrderCategory;
import com.fiap.techchallenge.fourlanches.kitchen.app.domain.entity.ProductionOrderItem;
import com.fiap.techchallenge.fourlanches.kitchen.app.domain.entity.ProductionOrderStatus;
import com.fiap.techchallenge.fourlanches.kitchen.app.domain.repository.ProductionRepository;
import com.fiap.techchallenge.fourlanches.kitchen.app.domain.valueobject.ProductionOrderIntent;
import com.fiap.techchallenge.fourlanches.kitchen.app.domain.valueobject.ProductionOrderIntentItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class KitchenUseCaseImplTest {

    @Mock
    private ProductionRepository productionRepository;

    @InjectMocks
    private KitchenUseCaseImpl kitchenUseCase;

    @Test
    void shouldAddOrderToProductionQueue() {
        var productionOrderIntentItem = ProductionOrderIntentItem.builder()
                .name("item")
                .category("extras")
                .description("description")
                .observation("observation")
                .quantity(1)
                .build();
        var productionOrderIntent = ProductionOrderIntent.builder()
                .orderId(1L)
                .orderItems(List.of(productionOrderIntentItem))
                .status(ProductionOrderStatus.QUEUED)
                .build();

        var wantedProductionOrder = productionOrderIntent.toProductionOrder();
        when(productionRepository.setOrCreate(any(ProductionOrder.class)))
                .thenReturn(wantedProductionOrder);

        var result = kitchenUseCase.addOrderToProductionQueue(productionOrderIntent);

        verify(productionRepository, times(1)).setOrCreate(any(ProductionOrder.class));
        assertEquals(wantedProductionOrder, result);

    }

    @Test
    void shouldThrowExceptionWhenPassingNullToAddOrderToProductionQueue() {
        assertThrows(IllegalArgumentException.class,
                () -> kitchenUseCase.addOrderToProductionQueue(null)
        );
        verifyNoInteractions(productionRepository);
    }

    @Test
    void shouldGetProductionOrderByOrderId() {
        var orderId = 1L;
        var now = LocalDateTime.now();
        var orderItem = ProductionOrderItem.builder()
                .name("item")
                .description("description")
                .quantity(1)
                .category(ProductionOrderCategory.EXTRAS)
                .observation("observations")
                .build();
        var productionOrder = ProductionOrder.builder()
                .orderId(1L)
                .orderItems(List.of(orderItem))
                .status(ProductionOrderStatus.QUEUED)
                .createdAt(now)
                .updatedAt(now)
                .build();
        when(productionRepository.getProductionOrderByOrderId(orderId))
                .thenReturn(productionOrder);

        var result = kitchenUseCase.getProductionOrderByOrderId(orderId);

        verify(productionRepository, times(1)).getProductionOrderByOrderId(orderId);
        assertEquals(productionOrder, result);
    }

    @Test
    void shouldGetAllProductionOrdersNotFinished() {
        var now = LocalDateTime.now();
        var orderItem = ProductionOrderItem.builder()
                .name("item")
                .description("description")
                .quantity(1)
                .category(ProductionOrderCategory.EXTRAS)
                .observation("observations")
                .build();
        var productionOrder = ProductionOrder.builder()
                .orderId(1L)
                .orderItems(List.of(orderItem))
                .status(ProductionOrderStatus.QUEUED)
                .createdAt(now)
                .updatedAt(now)
                .build();
        when(productionRepository.getAllProductionOrdersNotFinished())
                .thenReturn(Collections.singletonList(productionOrder));

        var results = kitchenUseCase.getAllProductionOrdersNotFinished();

        verify(productionRepository, times(1)).getAllProductionOrdersNotFinished();
        assertEquals(1, results.size());
    }

    @Test
    void shouldUpdateProductionOrderStatusById() {
        Long orderId = 1L;
        var now = LocalDateTime.now();
        var orderItem = ProductionOrderItem.builder()
                .name("item")
                .description("description")
                .quantity(1)
                .category(ProductionOrderCategory.EXTRAS)
                .observation("observations")
                .build();
        var productionOrder = ProductionOrder.builder()
                .orderId(1L)
                .orderItems(List.of(orderItem))
                .status(ProductionOrderStatus.QUEUED)
                .createdAt(now)
                .updatedAt(now)
                .build();
        when(productionRepository.getProductionOrderByOrderId(orderId))
                .thenReturn(productionOrder);
        when(productionRepository.setOrCreate(productionOrder))
                .thenReturn(productionOrder);

        var result = kitchenUseCase.updateProductionOrderStatusById(orderId, ProductionOrderStatus.FINISHED);

        verify(productionRepository, times(1)).getProductionOrderByOrderId(orderId);
        verify(productionRepository, times(1)).setOrCreate(productionOrder);
        assertEquals(productionOrder, result);
    }
}