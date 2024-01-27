package com.fiap.techchallenge.fourlanches.kitchen.app.domain.valueobject;

import com.fiap.techchallenge.fourlanches.kitchen.app.adapter.driven.data.entity.ProductionOrderItemJpaEntity;
import com.fiap.techchallenge.fourlanches.kitchen.app.domain.entity.ProductionOrder;
import com.fiap.techchallenge.fourlanches.kitchen.app.domain.entity.ProductionOrderItem;
import com.fiap.techchallenge.fourlanches.kitchen.app.domain.entity.ProductionOrderStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class ProductionOrderIntent {

    private Long orderId;
    private List<ProductionOrderIntentItem> orderItems;
    private ProductionOrderStatus status;

    public ProductionOrder toProductionOrder() {
        var now = LocalDateTime.now();
        return ProductionOrder.builder()
                .orderId(this.orderId)
                .orderItems(this.orderItems.stream().map(ProductionOrderIntentItem::toProductionOrderItem).toList())
                .createdAt(now)
                .updatedAt(now)
                .status(ProductionOrderStatus.QUEUED)
                .build();
    }
}
