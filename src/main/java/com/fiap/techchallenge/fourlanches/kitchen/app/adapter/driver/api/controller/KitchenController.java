package com.fiap.techchallenge.fourlanches.kitchen.app.adapter.driver.api.controller;

import com.fiap.techchallenge.fourlanches.kitchen.app.domain.entity.ProductionOrder;
import com.fiap.techchallenge.fourlanches.kitchen.app.domain.entity.ProductionOrderStatus;
import com.fiap.techchallenge.fourlanches.kitchen.app.domain.usecase.KitchenUseCase;
import com.fiap.techchallenge.fourlanches.kitchen.app.domain.valueobject.ProductionOrderIntent;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("kitchen/orders")
@AllArgsConstructor
public class KitchenController {

    private KitchenUseCase kitchenUseCase;

    @GetMapping(value = "/{orderId}", produces = "application/json")
    @ApiResponse(responseCode = "200")
    public ResponseEntity<ProductionOrder> getProductionOrderById(@PathVariable Long orderId) {
        var productionOrder = kitchenUseCase.getProductionOrderByOrderId(orderId);
        return ResponseEntity.ok(productionOrder);
    }

    @PostMapping(produces = "application/json")
    @ApiResponse(responseCode = "201")
    public ResponseEntity<ProductionOrder> insertNewProductionOrder(@RequestBody ProductionOrderIntent newOrder) {
        var createdOrder = kitchenUseCase.addOrderToProductionQueue(newOrder);
        return ResponseEntity.ok(createdOrder);
    }

    @GetMapping(value = "/queued", produces = "application/json")
    @ApiResponse(responseCode = "200")
    public ResponseEntity<List<ProductionOrder>> getAllProductionOrdersNotFinished() {
        var productionOrdersNotFinished = kitchenUseCase.getAllProductionOrdersNotFinished();
        return ResponseEntity.ok(productionOrdersNotFinished);
    }

    @PostMapping(value = "/{orderId}/in-production", produces = "application/json")
    @ApiResponse(responseCode = "200")
    public ResponseEntity<ProductionOrder> updateStatusToInProduction(@PathVariable Long orderId) {
        var inPreparationOrder = kitchenUseCase.updateProductionOrderStatusById(orderId, ProductionOrderStatus.IN_PREPARATION);
        return ResponseEntity.ok(inPreparationOrder);
    }

    @PostMapping(value = "/{orderId}/finished", produces = "application/json")
    @ApiResponse(responseCode = "200")
    public ResponseEntity<ProductionOrder> updateStatusToFinished(@PathVariable Long orderId) {
        var finishedOrder = kitchenUseCase.updateProductionOrderStatusById(orderId, ProductionOrderStatus.FINISHED);
        return ResponseEntity.ok(finishedOrder);
    }
}