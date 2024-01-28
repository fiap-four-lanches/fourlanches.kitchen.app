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

    public ProductionOrder toProductionOrder() {
        return ProductionOrder.builder()
                .id(this.id)
                .orderId(this.orderId)
                .orderItems(this.orderItems.stream().map(ProductionOrderItemJpaEntity::toProductionOrderItem).toList())
                .createdAt(this.createdAt)
                .updatedAt(this.updateAt)
                .status(ProductionOrderStatus.valueOf(this.status.toUpperCase()))
                .build();
    }

    public static ProductionOrderJpaEntity fromProductionOrder(ProductionOrder productionOrder) {
        var productionOrderJpaEntity = ProductionOrderJpaEntity.builder()
                .orderId(productionOrder.getOrderId())
                .createdAt(productionOrder.getCreatedAt())
                .updateAt(productionOrder.getUpdatedAt())
                .status(productionOrder.getStatus().toString())
                .build();

        productionOrderJpaEntity
                .setOrderItems(productionOrder.getOrderItems().stream().map(ProductionOrderItemJpaEntity::fromProductionOrderItem)
                        .toList());

        return productionOrderJpaEntity;
    }
}
