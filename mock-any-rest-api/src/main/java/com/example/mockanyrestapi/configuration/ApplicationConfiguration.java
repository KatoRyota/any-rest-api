package com.example.mockanyrestapi.configuration;

import com.example.anyrestapicore.configuration.ReloadablePropertySourceFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
        @PropertySource(value = "classpath:application.properties", factory = ReloadablePropertySourceFactory.class),
        @PropertySource(value = "classpath:error.properties", factory = ReloadablePropertySourceFactory.class)
})
public class ApplicationConfiguration {
}
