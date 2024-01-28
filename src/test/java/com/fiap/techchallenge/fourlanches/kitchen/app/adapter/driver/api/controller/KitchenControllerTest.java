package com.fiap.techchallenge.fourlanches.kitchen.app.adapter.driver.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.techchallenge.fourlanches.kitchen.app.domain.entity.ProductionOrder;
import com.fiap.techchallenge.fourlanches.kitchen.app.domain.entity.ProductionOrderStatus;
import com.fiap.techchallenge.fourlanches.kitchen.app.domain.usecase.KitchenUseCase;
import com.fiap.techchallenge.fourlanches.kitchen.app.domain.valueobject.ProductionOrderIntent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(KitchenController.class)
public class KitchenControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private KitchenUseCase kitchenUseCase;

    // Assuming Production order and OrderIntent are some defined POJOs
    private ProductionOrderIntent sampleOrderIntent = new ProductionOrderIntent();
    private ProductionOrder sampleOrder = new ProductionOrder();

    @Test
    public void shouldReturnProductionOrderGivenOrderId() throws Exception {
        // Given
        long orderId = 1L;

        given(kitchenUseCase.getProductionOrderByOrderId(orderId)).willReturn(sampleOrder);

        // When & Then
        this.mockMvc.perform(get("/kitchen/orders/" + orderId))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(sampleOrder)));

        verify(kitchenUseCase, times(1)).getProductionOrderByOrderId(orderId);
    }

    @Test
    public void shouldInsertNewProductionOrderWhenValidRequest() throws Exception {
        // Given
        given(kitchenUseCase.addOrderToProductionQueue(sampleOrderIntent)).willReturn(sampleOrder);

        // When & Then
        this.mockMvc.perform(post("/kitchen/orders")
                        .content(new ObjectMapper().writeValueAsString(sampleOrderIntent))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(sampleOrder)));

        verify(kitchenUseCase, times(1)).addOrderToProductionQueue(any(ProductionOrderIntent.class));
    }

    @Test
    public void shouldReturnAllProductionOrdersNotFinishedWhenRequested() throws Exception {
        // Given
        List<ProductionOrder> orders = Arrays.asList(sampleOrder, sampleOrder, sampleOrder);
        given(kitchenUseCase.getAllProductionOrdersNotFinished()).willReturn(orders);

        // When, Then
        this.mockMvc.perform(get("/kitchen/orders/queued"))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(orders)));

        verify(kitchenUseCase, times(1)).getAllProductionOrdersNotFinished();
    }

    @Test
    public void shouldReturnUpdatedProductionOrderToInProductionGivenOrderId() throws Exception {
        // Given
        long orderId = 1L;
        given(kitchenUseCase.updateProductionOrderStatusById(orderId, ProductionOrderStatus.IN_PREPARATION)).willReturn(sampleOrder);

        // When & Then
        this.mockMvc.perform(post("/kitchen/orders/" + orderId + "/in-production"))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(sampleOrder)));

        verify(kitchenUseCase, times(1)).updateProductionOrderStatusById(orderId, ProductionOrderStatus.IN_PREPARATION);
    }

    @Test
    public void shouldReturnUpdatedProductionOrderToFinishedGivenOrderId() throws Exception {
        // Given
        long orderId = 1L;
        given(kitchenUseCase.updateProductionOrderStatusById(orderId, ProductionOrderStatus.FINISHED)).willReturn(sampleOrder);

        // When & Then
        this.mockMvc.perform(post("/kitchen/orders/" + orderId + "/finished"))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(sampleOrder)));

        verify(kitchenUseCase, times(1)).updateProductionOrderStatusById(orderId, ProductionOrderStatus.FINISHED);
    }

}