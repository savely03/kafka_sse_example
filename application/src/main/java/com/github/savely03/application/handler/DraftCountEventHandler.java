package com.github.savely03.application.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.savely03.application.model.DraftCount;
import com.github.savely03.application.model.EventType;
import com.github.savely03.application.service.DraftCountEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DraftCountEventHandler implements EventHandler {

    private final ObjectMapper objectMapper;
    private final DraftCountEventService eventService;

    @Override
    public void handle(String event) {
        try {
            eventService.updateActualEvent(objectMapper.readValue(event, DraftCount.class));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EventType getEventType() {
        return EventType.DRAFT_COUNT;
    }

}
