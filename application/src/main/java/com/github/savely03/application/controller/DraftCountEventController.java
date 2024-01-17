package com.github.savely03.application.controller;

import com.github.savely03.application.model.DraftCount;
import com.github.savely03.application.service.DraftCountEventService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class DraftCountEventController extends AbstractEventController<DraftCount> {

    public DraftCountEventController(DraftCountEventService eventService) {
        super(eventService);
    }

    @GetMapping("/draft-count")
    @Override
    public Flux<DraftCount> get() {
        return super.get();
    }

}
