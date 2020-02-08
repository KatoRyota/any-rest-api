package com.example.anyrestapicore.configuration;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.springframework.core.env.PropertySource;
import org.springframework.util.StringUtils;

public class ReloadablePropertySource extends PropertySource {

    private final PropertiesConfiguration propertiesConfiguration;

    public ReloadablePropertySource(String name, String path) {

        super(StringUtils.isEmpty(name) ? path : name);

        try {
            this.propertiesConfiguration = new PropertiesConfiguration(path);
            this.propertiesConfiguration.setReloadingStrategy(new FileChangedReloadingStrategy());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object getProperty(String s) {
        return propertiesConfiguration.getProperty(s);
    }
}
