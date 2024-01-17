package com.github.savely03.application.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.savely03.application.mapper.JsonMapper;
import com.github.savely03.application.model.DraftCount;
import com.github.savely03.application.model.EventType;
import com.github.savely03.application.service.DraftCountEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DraftCountEventHandler implements EventHandler {

    private final DraftCountEventService eventService;
    private final JsonMapper<DraftCount> jsonMapper;

    @Override
    public void handleEvent(String event) {
        eventService.updateActualEvent(jsonMapper.readValue(event, DraftCount.class));
    }

    @Override
    public EventType getEventType() {
        return EventType.DRAFT_COUNT;
    }

}
