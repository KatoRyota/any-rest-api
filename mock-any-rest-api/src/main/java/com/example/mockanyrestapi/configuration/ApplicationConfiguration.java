package com.example.mockanyrestapi.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
        @PropertySource("file:config/application.properties"),
        @PropertySource("file:config/error.properties")
})
public class ApplicationConfiguration {
}
