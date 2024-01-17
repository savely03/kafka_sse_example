package com.github.savely03.application.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.savely03.application.model.DraftProducer;
import com.github.savely03.application.model.EventType;
import com.github.savely03.application.service.DraftProducerEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DraftProducerEventHandler implements EventHandler {

    private final ObjectMapper objectMapper;
    private final DraftProducerEventService eventService;

    @Override
    public void handle(String event) {
        try {
            eventService.updateActualEvent(objectMapper.readValue(event, DraftProducer.class));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EventType getEventType() {
        return EventType.DRAFT_PRODUCER;
    }

}
