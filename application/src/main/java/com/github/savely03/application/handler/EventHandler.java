package com.github.savely03.application.handler;

import com.github.savely03.application.model.EventType;

public interface EventHandler {

    void handleEvent(String event);

    EventType getEventType();

}
