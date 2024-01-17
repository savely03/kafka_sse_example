package com.github.savely03.application.controller;

import com.github.savely03.application.model.DraftProducer;
import com.github.savely03.application.service.DraftProducerEventService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class DraftProducerEventController extends AbstractEventController<DraftProducer> {

    public DraftProducerEventController(DraftProducerEventService eventService) {
        super(eventService);
    }

    @GetMapping("/draft-producer")
    @Override
    public Flux<DraftProducer> get() {
        return super.get();
    }

}
