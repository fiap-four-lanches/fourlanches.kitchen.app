package com.fiap.techchallenge.fourlanches.kitchen.app.domain.entity;

public enum ProductionOrderCategory {
    MEAL("meal"),
    DRINK("drink"),
    EXTRAS("extras");

    private final String value;

    ProductionOrderCategory(String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }
}
