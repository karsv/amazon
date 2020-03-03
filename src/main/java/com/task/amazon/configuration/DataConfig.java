package com.task.amazon.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource(value = "classpath:data.properties")
public class DataConfig {
    @Autowired
    private Environment environment;

    @Bean
    public String getDataPath() {
        return environment.getProperty("data.path");
    }

    @Bean
    public String getDataUrl() {
        return environment.getProperty("data.url");
    }

    @Bean
    public String getNewPathForDataFile() {
        return environment.getProperty("data.newpath");
    }
}
