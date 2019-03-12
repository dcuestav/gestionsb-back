package com.nidara.sabanasblancas.gestion.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class JDBCConfig implements EnvironmentAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(JDBCConfig.class);


    @Override
    public void setEnvironment(final Environment environment) {

        LOGGER.info("Mode: " + environment.getProperty("spring.profiles.active"));

        LOGGER.info("Connecting to: " + environment.getProperty("spring.datasource.url"));
        LOGGER.info("With username: " + environment.getProperty("spring.datasource.username"));
    }
}
