package com.fiap.techchallenge.fourlanches.kitchen.app.adapter.driver.queue;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class QueueConfiguration {

    public final static String X_REQUEST_ID = "X-Request-Id";

    private static String orderStatusQueue;

    private static String inPreparationQueue;

    private static String finishedQueue;

    private static String orderCancelQueue;

    private static String exchangeChannel;

    @Value("${queue.order.status.name}")
    public void setOrderStatusQueue(String value) {
        orderStatusQueue = value;
    }

    @Value("${queue.in-preparation.name}")
    public void setInPreparationQueue(String value) {
        inPreparationQueue = value;
    }

    @Value("${queue.finished.name}")
    public void setFinishedQueue(String value) {
        finishedQueue = value;
    }

    @Value("${queue.order.cancel.name}")
    public void setOrderCancelQueue(String value) {
        orderCancelQueue = value;
    }

    @Value("${queue.exchange.name}")
    public void setExchangeChannel(String value) {
        exchangeChannel = value;
    }

    @Bean
    public Queue orderStatusQueue() {
        return QueueBuilder.durable(orderStatusQueue).build();
    }

    @Bean
    public Queue inPreparationQueue() {
        return QueueBuilder.durable(inPreparationQueue).build();
    }

    @Bean
    public Queue finishedQueue() {
        return QueueBuilder.durable(finishedQueue).build();
    }

    @Bean
    public Queue orderCancelQueue() {
        return QueueBuilder.durable(orderCancelQueue).build();
    }

    @Bean
    public Binding orderStatusBinding() {
        return BindingBuilder.bind(orderStatusQueue()).to(channelExchange()).with(orderStatusQueue);
    }

    @Bean
    public Binding inPreparationBinding() {
        return BindingBuilder.bind(inPreparationQueue()).to(channelExchange()).with(inPreparationQueue);
    }

    @Bean
    public Binding finishedBinding() {
        return BindingBuilder.bind(finishedQueue()).to(channelExchange()).with(finishedQueue);
    }

    @Bean
    public Binding orderCancelBinding() {
        return BindingBuilder.bind(orderCancelQueue()).to(channelExchange()).with(orderCancelQueue);
    }

    @Bean
    public TopicExchange channelExchange() {
        return new TopicExchange(exchangeChannel, true, false);
    }
}
