package com.github.savely03.application.service;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public abstract class EventService<T> {

    private T actualEvent;
    private boolean isUpdated;

    private final Map<UUID, FluxSink<T>> subscribers = new ConcurrentHashMap<>();

    public T getActualEvent() {
        return actualEvent;
    }

    public void updateActualEvent(T event) {
        if (Objects.nonNull(event)) {
            actualEvent = event;
            isUpdated = true;
        }
    }

    public Flux<T> subscribe() {
        return Flux.create(fluxSink -> {
            UUID uuid = UUID.randomUUID();
            subscribers.put(uuid, fluxSink);
            log.info("added client with uuid - {}", uuid);
            fluxSink.onCancel(() -> {
                log.info("cancel client with uuid - {}", uuid);
                subscribers.remove(uuid);
            });
            if (Objects.nonNull(actualEvent)) {
                fluxSink.next(getActualEvent());
            }
        });
    }

    public void publish() {
        if (isUpdated && Objects.nonNull(actualEvent)) {
            subscribers.values().forEach(fluxSink -> fluxSink.next(actualEvent));
            isUpdated = false;
        }
    }

}
