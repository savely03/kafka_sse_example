package com.github.savely03.application.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.savely03.application.mapper.JsonMapper;
import com.github.savely03.application.model.DraftProducer;
import com.github.savely03.application.model.EventType;
import com.github.savely03.application.service.DraftProducerEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DraftProducerEventHandler implements EventHandler {

    private final DraftProducerEventService eventService;
    private final JsonMapper<DraftProducer> jsonMapper;

    @Override
    public void handleEvent(String event) {
        eventService.updateActualEvent(jsonMapper.readValue(event, DraftProducer.class));
    }

    @Override
    public EventType getEventType() {
        return EventType.DRAFT_PRODUCER;
    }

}
