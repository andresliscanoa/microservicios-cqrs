package com.lliscano.microservicioconsumer.controller;

import com.lliscano.commons.dto.KafkaEventDTO;
import com.lliscano.microservicioconsumer.service.ConsumerService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class ConsumerController {

    private final ConsumerService service;
    private static final String TOPIC = "user-event-topic";
    private static final String GROUP = "user-event-group";

    @KafkaListener(topics = TOPIC, groupId = GROUP)
    public void listenerUsersEvents(KafkaEventDTO kafkaEventDTO) {
        this.service.syncUsers(kafkaEventDTO);
    }
}
