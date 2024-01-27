package com.fiap.techchallenge.fourlanches.kitchen.app.adapter.driven.data.entity;

import com.fiap.techchallenge.fourlanches.kitchen.app.domain.entity.ProductionOrder;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("ProductionOrderQueuedRedisEntity")
public class ProductionOrderQueuedRedisEntity {
    private Long id;
    private ProductionOrder data;

    public ProductionOrder toProductionOrderQueued() {
        return this.data;
    }

}
