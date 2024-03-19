package com.fiap.techchallenge.fourlanches.kitchen.app.domain.valueobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fiap.techchallenge.fourlanches.kitchen.app.domain.entity.ProductionOrder;
import com.fiap.techchallenge.fourlanches.kitchen.app.domain.entity.ProductionOrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProductionOrderIntent {

    private Long orderId;
    private List<ProductionOrderIntentItem> orderItems;
    private ProductionOrderStatus status;

    @JsonIgnore
    private String originalRequestId;

    public ProductionOrder toProductionOrder() {
        var now = LocalDateTime.now();
        return ProductionOrder.builder()
                .orderId(this.orderId)
                .orderItems(this.orderItems.stream().map(ProductionOrderIntentItem::toProductionOrderItem).toList())
                .createdAt(now)
                .updatedAt(now)
                .status(ProductionOrderStatus.QUEUED)
                .originalRequestId(this.originalRequestId)
                .build();
    }
}
