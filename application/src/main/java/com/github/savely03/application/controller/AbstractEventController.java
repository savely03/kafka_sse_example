package com.github.savely03.application.controller;

import com.github.savely03.application.service.EventService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public abstract class AbstractEventController<T> {

    public final EventService<T> eventService;

    public Flux<T> get() {
        return eventService.subscribe();
    }

}
