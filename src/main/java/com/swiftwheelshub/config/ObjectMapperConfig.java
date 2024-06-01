package com.swiftwheelshub.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class ObjectMapperConfig {

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        return Jackson2ObjectMapperBuilder.json()
                .modules(new JavaTimeModule())
                .featuresToEnable(MapperFeature.ALLOW_COERCION_OF_SCALARS)
                .featuresToDisable(
                        SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS,
                        DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE
                )
                .build();
    }

}
