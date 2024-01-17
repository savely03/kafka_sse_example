package com.github.savely03.application.controller;

import com.github.savely03.application.model.DraftCount;
import com.github.savely03.application.service.DraftCountClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
public class DraftCountSseController {

    private final DraftCountClientService clientService;

    @GetMapping("/draft-count")
    public Flux<DraftCount> follow() {
        return clientService.followDraftProducer();
    }

}
