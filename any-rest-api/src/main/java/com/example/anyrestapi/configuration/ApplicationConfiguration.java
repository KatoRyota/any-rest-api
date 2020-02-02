package com.example.anyrestapi.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
        @PropertySource("classpath:application.properties"),
        @PropertySource("classpath:data-source.properties"),
        @PropertySource("classpath:error.properties")
})
public class ApplicationConfiguration {
}
