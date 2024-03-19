package com.fiap.techchallenge.fourlanches.kitchen.app.adapter.driven.data.entity;

import com.fiap.techchallenge.fourlanches.kitchen.app.domain.entity.ProductionOrder;
import com.fiap.techchallenge.fourlanches.kitchen.app.domain.entity.ProductionOrderStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class ProductionOrderJpaEntityTest {

    @Test
    public void shouldConvertFullJpaEntityToDomainEntity() {
        // Given
        var jpaEntity = new ProductionOrderJpaEntity();
        jpaEntity.setId(1L);
        jpaEntity.setOrderId(123L);
        jpaEntity.setCreatedAt(LocalDateTime.now());
        jpaEntity.setUpdateAt(LocalDateTime.now());
        jpaEntity.setStatus("queued");
        jpaEntity.setDetail("detail");
        jpaEntity.setOriginalRequestId("req-id");
        jpaEntity.setOrderItems(Collections.emptyList());

        // When
        var productionOrder = jpaEntity.toProductionOrder();

        // Then
        assertNotNull(productionOrder);
        assertEquals(jpaEntity.getId(), productionOrder.getId());
        assertEquals(jpaEntity.getOrderId(), productionOrder.getOrderId());
        assertEquals(jpaEntity.getCreatedAt(), productionOrder.getCreatedAt());
        assertEquals(jpaEntity.getUpdateAt(), productionOrder.getUpdatedAt());
        assertEquals(ProductionOrderStatus.valueOf(jpaEntity.getStatus().toUpperCase()), productionOrder.getStatus());
        assertEquals(jpaEntity.getDetail(), productionOrder.getDetail());
        assertEquals(jpaEntity.getOriginalRequestId(), productionOrder.getOriginalRequestId());
        assertEquals(jpaEntity.getOrderItems().size(), productionOrder.getOrderItems().size());
    }

    @Test
    public void shouldHandleEmptyJpaEntityToDomainEntityConversion() {
        // Given
        var jpaEntity = new ProductionOrderJpaEntity();

        // When
        var productionOrder = jpaEntity.toProductionOrder();

        // Then
        assertNotNull(productionOrder);
        assertNull(productionOrder.getStatus());
        assertNull(productionOrder.getOrderItems());
    }

    @Test
    public void shouldConvertFullDomainEntityToJpaEntity() {
        // Given
        var productionOrder = ProductionOrder.builder()
                .orderId(123L)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .status(ProductionOrderStatus.QUEUED)
                .detail("detail")
                .originalRequestId("req-id")
                .orderItems(List.of())
                .build();

        // When
        var jpaEntity = ProductionOrderJpaEntity.fromProductionOrder(productionOrder);

        // Then
        assertNotNull(jpaEntity);
        assertEquals(productionOrder.getId(), jpaEntity.getId());
        assertEquals(productionOrder.getOrderId(), jpaEntity.getOrderId());
        assertEquals(productionOrder.getCreatedAt(), jpaEntity.getCreatedAt());
        assertEquals(productionOrder.getUpdatedAt(), jpaEntity.getUpdateAt());
        assertEquals(productionOrder.getStatus().toString(), jpaEntity.getStatus());
        assertEquals(productionOrder.getDetail(), jpaEntity.getDetail());
        assertEquals(productionOrder.getOriginalRequestId(), jpaEntity.getOriginalRequestId());
        assertEquals(productionOrder.getOrderItems().size(), jpaEntity.getOrderItems().size());
    }

    @Test
    public void shouldHandleEmptyDomainEntityToJpaEntityConversion() {
        // Given
        var productionOrder = new ProductionOrder();

        // When
        var jpaEntity = ProductionOrderJpaEntity.fromProductionOrder(productionOrder);

        // Then
        assertNotNull(jpaEntity);
    }
}