package com.carrentalservice.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestUtils {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().registerModule(new JavaTimeModule());

    public static <T> T getResourceAsJson(String resourceName, Class<T> valueType) {
        try {
            return OBJECT_MAPPER.readValue(getRespurceAsString(resourceName), valueType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed getting resource: " + resourceName);
        }
    }

    private static String getRespurceAsString(String resourceName) {
        URL resource = TestUtils.class.getResource(resourceName);

        if (resource == null) {
            throw new RuntimeException("Failed getting resource: " + resourceName);
        }

        try {
            return new String(Files.readAllBytes(Paths.get(resource.toURI())));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException("Failed getting resource: " + resourceName);
        }
    }

}
