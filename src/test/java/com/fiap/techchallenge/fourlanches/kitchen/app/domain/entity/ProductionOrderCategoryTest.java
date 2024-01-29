package com.fiap.techchallenge.fourlanches.kitchen.app.domain.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductionOrderCategoryTest {
    @Test
    public void shouldReturnCorrectStringRepresentation() {
        // When
        String mealCategory = ProductionOrderCategory.MEAL.toString();
        String drinkCategory = ProductionOrderCategory.DRINK.toString();
        String extrasCategory = ProductionOrderCategory.EXTRAS.toString();

        // Then
        assertEquals("meal", mealCategory);
        assertEquals("drink", drinkCategory);
        assertEquals("extras", extrasCategory);
    }

}