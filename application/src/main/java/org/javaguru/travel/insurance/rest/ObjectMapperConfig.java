package org.javaguru.travel.insurance.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * Конфигурация для поддержки LocalDate при логирование
 */
public class ObjectMapperConfig {

    public static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        // Регистрируем модуль для поддержки Java 8 Date/Time API
        mapper.registerModule(new JavaTimeModule());
        // Отключаем запись дат как таймстампов (используем ISO-8601 формат)
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return mapper;
    }
}