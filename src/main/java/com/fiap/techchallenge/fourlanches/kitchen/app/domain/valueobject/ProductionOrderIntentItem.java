package com.fiap.techchallenge.fourlanches.kitchen.app.domain.valueobject;

import com.fiap.techchallenge.fourlanches.kitchen.app.domain.entity.ProductionOrderCategory;
import com.fiap.techchallenge.fourlanches.kitchen.app.domain.entity.ProductionOrderItem;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductionOrderIntentItem {
    private String name;
    private String category;
    private String description;
    private int quantity;
    private String observation;

    public ProductionOrderItem toProductionOrderItem() {
        return ProductionOrderItem.builder()
                .name(this.name)
                .category(ProductionOrderCategory.valueOf(this.category.toUpperCase()))
                .description(this.description)
                .quantity(this.quantity)
                .observation(this.observation)
                .build();
    }
}

