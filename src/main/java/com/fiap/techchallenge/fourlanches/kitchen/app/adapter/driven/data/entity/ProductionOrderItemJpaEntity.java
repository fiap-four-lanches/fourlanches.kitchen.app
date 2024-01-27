package com.fiap.techchallenge.fourlanches.kitchen.app.adapter.driven.data.entity;


import com.fiap.techchallenge.fourlanches.kitchen.app.domain.entity.ProductionOrderCategory;
import com.fiap.techchallenge.fourlanches.kitchen.app.domain.entity.ProductionOrderItem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "order_items")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductionOrderItemJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "order_id", nullable = false)
    private ProductionOrderJpaEntity order;
    private String name;
    private String category;
    private String description;
    private int quantity;
    private String observation;

    public ProductionOrderItem toProductionOrderItem() {
        return ProductionOrderItem.builder()
                .id(this.id)
                .name(this.name)
                .category(ProductionOrderCategory.valueOf(this.category))
                .description(this.description)
                .quantity(this.quantity)
                .observation(this.observation)
                .build();
    }

    public static ProductionOrderItemJpaEntity fromProductionOrderItem(ProductionOrderItem productionOrderItem) {
        return ProductionOrderItemJpaEntity.builder()
                .name(productionOrderItem.getName())
                .category(productionOrderItem.getCategory().toString())
                .description(productionOrderItem.getDescription())
                .quantity(productionOrderItem.getQuantity())
                .observation(productionOrderItem.getObservation())
                .build();
    }
}
