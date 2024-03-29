package com.github.savely03.application.service;

import com.github.savely03.application.handler.EventHandler;
import com.github.savely03.application.model.EventType;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.Headers;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MessageService {

    private final Map<EventType, EventHandler> eventHandlers;

    public MessageService(List<EventHandler> eventHandlers) {
        this.eventHandlers = new ConcurrentHashMap<>(eventHandlers.stream()
                .collect(Collectors.toMap(EventHandler::getEventType, Function.identity())));
    }

    @KafkaListener(topics = "${app.kafka.consumer.topic}")
    public void receive(ConsumerRecord<String, String> consumerRecord) {
        Headers headers = consumerRecord.headers();
        String headerValue = parseEventTypeHeader(headers.lastHeader("eventType"));
        eventHandlers.get(EventType.valueOf(headerValue)).handleEvent(consumerRecord.value());
    }


    private String parseEventTypeHeader(Header header) {
        return new String(header.value(), StandardCharsets.UTF_8);
    }

}
