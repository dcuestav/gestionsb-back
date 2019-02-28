package com.nidara.sabanasblancas.gestion.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;

@Configuration
@PropertySources({

        // Desarrollo
        @PropertySource("classpath:persistence-development.properties"),

        // Producción
        @PropertySource(value = "file:/etc/nidara/sabanasblancas/persistence.properties", ignoreResourceNotFound = true),
})
public class JDBCConfig implements EnvironmentAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(JDBCConfig.class);


    @Override
    public void setEnvironment(final Environment environment) {

        LOGGER.info("Mode: " + environment.getProperty("spring.profiles.active"));

        LOGGER.info("Connecting to: " + environment.getProperty("spring.datasource.url"));
        LOGGER.info("With username: " + environment.getProperty("spring.datasource.username"));
    }
}
