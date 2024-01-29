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
        jpaEntity.setCreatedAt(LocalDateTime.now());
        jpaEntity.setOrderId(123L);
        jpaEntity.setStatus("queued");
        jpaEntity.setUpdateAt(LocalDateTime.now());
        jpaEntity.setOrderItems(Collections.emptyList());

        // When
        var productionOrder = jpaEntity.toProductionOrder();

        // Then
        assertNotNull(productionOrder);
        assertEquals(jpaEntity.getId(), productionOrder.getId());
        assertEquals(jpaEntity.getCreatedAt(), productionOrder.getCreatedAt());
        assertEquals(jpaEntity.getOrderId(), productionOrder.getOrderId());
        assertEquals(ProductionOrderStatus.valueOf(jpaEntity.getStatus().toUpperCase()), productionOrder.getStatus());
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
                .createdAt(LocalDateTime.now())
                .orderId(123L)
                .status(ProductionOrderStatus.QUEUED)
                .updatedAt(LocalDateTime.now())
                .orderItems(List.of())
                .build();

        // When
        var jpaEntity = ProductionOrderJpaEntity.fromProductionOrder(productionOrder);

        // Then
        assertNotNull(jpaEntity);
        assertEquals(productionOrder.getId(), jpaEntity.getId());
        assertEquals(productionOrder.getCreatedAt(), jpaEntity.getCreatedAt());
        assertEquals(productionOrder.getOrderId(), jpaEntity.getOrderId());
        assertEquals(productionOrder.getStatus().toString(), jpaEntity.getStatus());
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