package com.fiap.techchallenge.fourlanches.kitchen.app.adapter.driven.data.repository;

import com.fiap.techchallenge.fourlanches.kitchen.app.adapter.driven.data.ProductionOrderJpaRepository;
import com.fiap.techchallenge.fourlanches.kitchen.app.adapter.driven.data.entity.ProductionOrderJpaEntity;
import com.fiap.techchallenge.fourlanches.kitchen.app.adapter.driven.data.expcetion.ProductionOrderNotFoundException;
import com.fiap.techchallenge.fourlanches.kitchen.app.domain.entity.ProductionOrder;
import com.fiap.techchallenge.fourlanches.kitchen.app.domain.entity.ProductionOrderCategory;
import com.fiap.techchallenge.fourlanches.kitchen.app.domain.entity.ProductionOrderItem;
import com.fiap.techchallenge.fourlanches.kitchen.app.domain.entity.ProductionOrderStatus;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductionRepositoryImplTest {

    @Mock
    private ProductionOrderJpaRepository jpaRepository;

    @InjectMocks
    private ProductionRepositoryImpl productionRepository;

    @Test
    void shouldSetOrCreateProductionOrder() {
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
        when(jpaRepository.save(any(ProductionOrderJpaEntity.class)))
                .thenReturn(ProductionOrderJpaEntity.fromProductionOrder(productionOrder));

        var result = productionRepository.setOrCreate(productionOrder);

        verify(jpaRepository, times(1)).save(any(ProductionOrderJpaEntity.class));
        assertEquals(productionOrder, result);
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
        when(jpaRepository.getProductionOrderJpaEntityByOrderId(orderId))
                .thenReturn(Optional.of(ProductionOrderJpaEntity.fromProductionOrder(productionOrder)));

        var result = productionRepository.getProductionOrderByOrderId(orderId);

        verify(jpaRepository, times(1)).getProductionOrderJpaEntityByOrderId(orderId);
        assertEquals(productionOrder, result);
    }

    @Test
    void shouldThrowProductionOrderNotFoundExceptionWhenOrderIdNotFound() {
        var orderId = 1L;
        when(jpaRepository.getProductionOrderJpaEntityByOrderId(orderId))
                .thenReturn(Optional.empty());

        assertThrows(ProductionOrderNotFoundException.class,
                () -> productionRepository.getProductionOrderByOrderId(orderId));

        verify(jpaRepository, times(1)).getProductionOrderJpaEntityByOrderId(orderId);
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
        when(jpaRepository.getAllProductionOrderNotFinished())
                .thenReturn(Collections.singletonList(ProductionOrderJpaEntity.fromProductionOrder(productionOrder)));

        var results = productionRepository.getAllProductionOrdersNotFinished();

        verify(jpaRepository, times(1)).getAllProductionOrderNotFinished();
        assertEquals(1, results.size());
    }

    @Test
    void shouldReturnEmptyListWhenNoUnfinishedProductionOrders() {
        when(jpaRepository.getAllProductionOrderNotFinished())
                .thenReturn(Collections.emptyList());

        var results = productionRepository.getAllProductionOrdersNotFinished();

        verify(jpaRepository, times(1)).getAllProductionOrderNotFinished();
        assertTrue(results.isEmpty());
    }
}