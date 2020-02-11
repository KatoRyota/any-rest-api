package com.example.anyrestapi.configuration;

import com.example.anyrestapicore.configuration.ReloadablePropertySourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.client.RestTemplate;

@Configuration
@PropertySources({
        @PropertySource(value = "classpath:application.properties", factory = ReloadablePropertySourceFactory.class),
        @PropertySource("classpath:data-source.properties"),
        @PropertySource(value = "classpath:error.properties", factory = ReloadablePropertySourceFactory.class)
})
public class ApplicationConfiguration {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
