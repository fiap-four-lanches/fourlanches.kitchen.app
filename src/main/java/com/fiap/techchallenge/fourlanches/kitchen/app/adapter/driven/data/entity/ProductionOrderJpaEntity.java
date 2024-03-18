package com.fiap.techchallenge.fourlanches.kitchen.app.adapter.driven.data.entity;

import com.fiap.techchallenge.fourlanches.kitchen.app.domain.entity.ProductionOrder;
import com.fiap.techchallenge.fourlanches.kitchen.app.domain.entity.ProductionOrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductionOrderJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;
    @Column(name = "order_id")
    private Long orderId;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.PERSIST)
    private List<ProductionOrderItemJpaEntity> orderItems;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updateAt;
    private String status;
    private String detail;
    @Column(name = "original_request_id")
    private String originalRequestId;

    public ProductionOrder toProductionOrder() {
        var productionOrder = ProductionOrder.builder()
                .id(this.id)
                .orderId(this.orderId)
                .createdAt(this.createdAt)
                .updatedAt(this.updateAt)
                .detail(detail)
                .originalRequestId(originalRequestId)
                .build();

        if (this.status != null) {
            productionOrder.setStatus(ProductionOrderStatus.valueOf(this.status.toUpperCase()));
        }

        if (this.orderItems != null) {
            productionOrder.setOrderItems(this.orderItems.stream().map(ProductionOrderItemJpaEntity::toProductionOrderItem).toList());
        }

        return productionOrder;
    }

    public static ProductionOrderJpaEntity fromProductionOrder(ProductionOrder productionOrder) {
        var productionOrderJpaEntity = ProductionOrderJpaEntity.builder()
                .orderId(productionOrder.getOrderId())
                .createdAt(productionOrder.getCreatedAt())
                .updateAt(productionOrder.getUpdatedAt())
                .detail(productionOrder.getDetail())
                .originalRequestId(productionOrder.getOriginalRequestId())
                .build();

        if (productionOrder.getStatus() != null) {
            productionOrderJpaEntity.setStatus(productionOrder.getStatus().toString());
        }

        if (productionOrder.getOrderItems() != null) {
            productionOrderJpaEntity
                    .setOrderItems(productionOrder.getOrderItems().stream().map(ProductionOrderItemJpaEntity::fromProductionOrderItem)
                            .toList());
        }

        return productionOrderJpaEntity;
    }
}
