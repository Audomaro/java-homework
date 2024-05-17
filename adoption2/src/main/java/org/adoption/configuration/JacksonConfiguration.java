package org.adoption.configuration;

import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfiguration {

    public JacksonConfiguration(Jackson2ObjectMapperBuilder builder) {
        builder.featuresToDisable(SerializationFeature.WRITE_NULL_MAP_VALUES);
    }
}