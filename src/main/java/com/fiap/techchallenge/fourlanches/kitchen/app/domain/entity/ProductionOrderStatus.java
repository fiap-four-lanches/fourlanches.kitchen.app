package com.fiap.techchallenge.fourlanches.kitchen.app.domain.entity;

public enum ProductionOrderStatus {
    QUEUED("queued"),
    IN_PREPARATION("in_preparation"),
    FINISHED("finished");

    private final String value;

    ProductionOrderStatus(String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }
}
