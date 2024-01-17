package com.github.savely03.application.service;

import com.github.savely03.application.model.DraftProducer;
import com.github.savely03.application.model.EventType;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class DraftProducerEventService{

    private DraftProducer actualEvent = new DraftProducer();


    public DraftProducer getActualEvent() {
        return actualEvent;
    }


    public void updateActualEvent(DraftProducer event) {
        if (Objects.nonNull(event)) {
            actualEvent = event;
        }
    }

}
