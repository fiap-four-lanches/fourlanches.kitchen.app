package com.fiap.techchallenge.fourlanches.kitchen.app.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductionOrder {
    private Long id;
    private Long orderId;
    private List<ProductionOrderItem> orderItems;
    private ProductionOrderStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
