package com.github.savely03.application.service;

import reactor.core.publisher.FluxSink;

public interface EventService<T> {

    void subscribe(FluxSink<T> fluxSink);

    void publish();

}
