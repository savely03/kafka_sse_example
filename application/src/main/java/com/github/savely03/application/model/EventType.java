package com.github.savely03.application.model;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum EventType {

    DRAFT_PRODUCER("draftProd"),
    DRAFT_COUNT("draftCount");

    private final String value;

    EventType(String value) {
        this.value = value;
    }

    public static EventType findEventTypeByValue(String value) {
        return Arrays.stream(EventType.values())
                .filter(eventType -> eventType.getValue().equals(value))
                .findFirst()
                .orElse(null);
    }

}
