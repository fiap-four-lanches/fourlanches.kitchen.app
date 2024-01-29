package com.fiap.techchallenge.fourlanches.kitchen.app.domain.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductionOrderItem {
    private Long id;
    private String name;
    private ProductionOrderCategory category;
    private String description;
    private int quantity;
    private String observation;
}
