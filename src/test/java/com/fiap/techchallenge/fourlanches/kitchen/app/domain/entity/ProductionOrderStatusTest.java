package com.fiap.techchallenge.fourlanches.kitchen.app.domain.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductionOrderStatusTest {
    @Test
    public void shouldReturnCorrectStringRepresentation() {
        // When
        String queuedStatus = ProductionOrderStatus.QUEUED.toString();
        String inPreparationStatus = ProductionOrderStatus.IN_PREPARATION.toString();
        String finishedStatus = ProductionOrderStatus.FINISHED.toString();

        // Then
        assertEquals("queued", queuedStatus);
        assertEquals("in_preparation", inPreparationStatus);
        assertEquals("finished", finishedStatus);
    }
}