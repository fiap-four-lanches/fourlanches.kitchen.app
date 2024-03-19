package com.fiap.techchallenge.fourlanches.kitchen.app.application.dto;


import com.fiap.techchallenge.fourlanches.kitchen.app.domain.valueobject.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusQueueMessageDTO implements Serializable {

    private Long orderId;
    private OrderStatus status;
    private String origin;

    public static OrderStatusQueueMessageDTO fromOrder(Long orderId, OrderStatus orderStatus) {
        return OrderStatusQueueMessageDTO.builder()
                .orderId(orderId)
                .status(orderStatus)
                .origin("kitchen")
                .build();
    }
}
