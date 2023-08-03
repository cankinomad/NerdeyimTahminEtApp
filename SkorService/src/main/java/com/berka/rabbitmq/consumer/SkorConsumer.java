package com.berka.rabbitmq.consumer;

import com.berka.rabbitmq.model.SkorModel;
import com.berka.service.SkorService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SkorConsumer {

    private final SkorService service;

    @RabbitListener(queues = "skor-queue")
    public void updateSkor(SkorModel model) {
        service.saveOrUpdateSkor(model);
    }
}
