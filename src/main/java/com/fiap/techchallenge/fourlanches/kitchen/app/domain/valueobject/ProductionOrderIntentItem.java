package com.fiap.techchallenge.fourlanches.kitchen.app.domain.valueobject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fiap.techchallenge.fourlanches.kitchen.app.domain.entity.ProductionOrderCategory;
import com.fiap.techchallenge.fourlanches.kitchen.app.domain.entity.ProductionOrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductionOrderIntentItem {
    private Long productId;
    private String name;
    private String category;
    private String description;
    private int quantity;
    private String observation;

    public ProductionOrderItem toProductionOrderItem() {
        var productionOrderItem = ProductionOrderItem.builder()
                .id(this.productId)
                .name(this.name)
                .description(this.description)
                .quantity(this.quantity)
                .observation(this.observation)
                .build();
        if (this.category != null) {
            productionOrderItem.setCategory(ProductionOrderCategory.valueOf(this.category.toUpperCase()));
        }
        return productionOrderItem;
    }
}

