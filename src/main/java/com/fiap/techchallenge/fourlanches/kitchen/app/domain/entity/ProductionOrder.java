package com.fiap.techchallenge.fourlanches.kitchen.app.domain.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class ProductionOrder {
    private Long id;
    private Long orderId;
    private List<ProductionOrderItem> orderItems;
    private ProductionOrderStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
