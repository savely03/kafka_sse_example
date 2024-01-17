package com.github.savely03.application.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class JsonMapper<T> {

    private final ObjectMapper objectMapper;

    public T readValue(String value, Class<T> clazz) {
        T res = null;
        try {
            res = objectMapper.readValue(value, clazz);
        } catch (JsonProcessingException e) {
            log.error("Deserialization error from String to {}", clazz.getTypeName(), e);
        }
        return res;
    }

    public String writeValueAsString(T value) {
        String res = null;
        try {
            res = objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            log.error("Serialization error from {} to String", value.getClass().getTypeName());
        }
        return res;
    }

}
