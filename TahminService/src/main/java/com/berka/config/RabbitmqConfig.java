package com.berka.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    private String exchange = "tahmin-exchange";
    private String queueSkor = "skor-queue";
    private String bindingKey = "skor-bindingKey";


    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(exchange);
    }

    @Bean
    Queue skorQueue() {
        return new Queue(queueSkor);
    }

    @Bean
    public Binding skorBindingKey(final DirectExchange directExchange, final Queue skorQueue) {
        return BindingBuilder.bind(skorQueue).to(directExchange).with(bindingKey);
    }
}
