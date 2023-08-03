package com.berka.rabbitmq.producer;

import com.berka.rabbitmq.model.SkorModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SkorProducer {

    private final RabbitTemplate rabbitTemplate;

    private String exchange = "tahmin-exchange";
    private String bindingKey = "skor-bindingKey";

    public void sendSkor(SkorModel model) {
        rabbitTemplate.convertAndSend(exchange,bindingKey,model);
    }
}
