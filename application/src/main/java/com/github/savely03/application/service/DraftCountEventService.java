package com.github.savely03.application.service;

import com.github.savely03.application.model.DraftCount;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class DraftCountEventService {

    private DraftCount actualEvent = new DraftCount();


    public DraftCount getActualEvent() {
        return actualEvent;
    }


    public void updateActualEvent(DraftCount event) {
        if (Objects.nonNull(event)) {
            actualEvent = event;
        }
    }

}
