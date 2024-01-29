package com.fiap.techchallenge.fourlanches.kitchen.app.adapter.driven.data.entity;

import com.fiap.techchallenge.fourlanches.kitchen.app.domain.entity.ProductionOrderCategory;
import com.fiap.techchallenge.fourlanches.kitchen.app.domain.entity.ProductionOrderItem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProductionOrderItemJpaEntityTest {

    @Test
    void shouldConvertFullJpaEntityToDomainEntity() {
        // given
        ProductionOrderItemJpaEntity jpaEntity = new ProductionOrderItemJpaEntity();
        jpaEntity.setId(1L);
        jpaEntity.setName("Item name");
        jpaEntity.setCategory("extras");
        jpaEntity.setDescription("Item description");
        jpaEntity.setQuantity(2);
        jpaEntity.setObservation("Observation");

        // when
        ProductionOrderItem orderItem = jpaEntity.toProductionOrderItem();

        // then
        assertNotNull(orderItem);
        assertEquals(jpaEntity.getId(), orderItem.getId());
        assertEquals(jpaEntity.getName(), orderItem.getName());
        assertEquals(ProductionOrderCategory.valueOf(jpaEntity.getCategory().toUpperCase()), orderItem.getCategory());
        assertEquals(jpaEntity.getDescription(), orderItem.getDescription());
        assertEquals(jpaEntity.getQuantity(), orderItem.getQuantity());
        assertEquals(jpaEntity.getObservation(), orderItem.getObservation());
    }

    @Test
    void shouldHandleEmptyJpaEntityToDomainEntityConversion() {
        // given
        var jpaEntity = new ProductionOrderItemJpaEntity();

        // when
        var orderItem = jpaEntity.toProductionOrderItem();

        // then
        assertNotNull(orderItem);
    }

    @Test
    void shouldConvertFullDomainEntityToJpaEntity() {
        // given
        var orderItem = ProductionOrderItem.builder()
                .name("Item name")
                .category(ProductionOrderCategory.EXTRAS)
                .description("Item description")
                .quantity(2)
                .observation("Observation")
                .build();

        // when
        var jpaEntity = ProductionOrderItemJpaEntity.fromProductionOrderItem(orderItem);

        // then
        assertNotNull(jpaEntity);
        assertEquals(orderItem.getId(), jpaEntity.getId());
        assertEquals(orderItem.getName(), jpaEntity.getName());
        assertEquals(orderItem.getCategory().toString(), jpaEntity.getCategory());
        assertEquals(orderItem.getDescription(), jpaEntity.getDescription());
        assertEquals(orderItem.getQuantity(), jpaEntity.getQuantity());
        assertEquals(orderItem.getObservation(), jpaEntity.getObservation());
    }

    @Test
    void shouldHandleEmptyDomainEntityToJpaEntityConversion() {
        // given
        var orderItem = ProductionOrderItem.builder().build();

        // when
        var jpaEntity = ProductionOrderItemJpaEntity.fromProductionOrderItem(orderItem);

        // then
        assertNotNull(jpaEntity);
    }
}