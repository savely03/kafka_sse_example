package com.github.savely03.application.service;

import com.github.savely03.application.model.DraftProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class DraftProducerClientService {

    private final DraftProducerEventService draftProducerEventService;
    private final Map<UUID, FluxSink<DraftProducer>> clients = new ConcurrentHashMap<>();
    
    public Flux<DraftProducer> followDraftProducer() {
        return Flux.create(fluxSink -> {
            UUID uuid = UUID.randomUUID();
            clients.put(uuid, fluxSink);
            log.info("added client with uuid - {}", uuid);
            fluxSink.onCancel(() -> {
                log.info("cancel client with uuid - {}", uuid);
                clients.remove(uuid);
            });
        });
        
    }

    @Scheduled(fixedRate = 5000)
    public void send() {
        clients.values().forEach(fluxSink -> fluxSink.next(draftProducerEventService.getActualEvent()));
    }


}
