package com.github.savely03.application.controller;

import com.github.savely03.application.model.DraftProducer;
import com.github.savely03.application.service.DraftProducerClientService;
import com.github.savely03.application.service.DraftProducerEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

@RestController
@RequiredArgsConstructor
public class DraftProducerSseController {

    private final DraftProducerEventService eventService;

    @GetMapping("/draft-producer")
    public Flux<DraftProducer> follow() {
        return Flux.interval(Duration.ofSeconds(5))
                .onBackpressureDrop()
                .map(e -> eventService.getActualEvent())
                .subscribeOn(Schedulers.boundedElastic());
    }

}
