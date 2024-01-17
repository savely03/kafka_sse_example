package com.github.savely03.application.scheduler;

import com.github.savely03.application.service.EventService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventScheduler {

    private final List<EventService> eventServices;

    public void send() {
        Flux.interval(Duration.ofSeconds(5))
                .onBackpressureDrop()
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(s -> {
                    log.debug("Trying to publish events");
                    eventServices.forEach(EventService::publish);
                });
    }

    @PostConstruct
    private void init() {
        send();
    }

}
