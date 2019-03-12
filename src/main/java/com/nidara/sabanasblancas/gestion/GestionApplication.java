package com.nidara.sabanasblancas.gestion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@ComponentScan("com.nidara.sabanasblancas.gestion")
@PropertySources({
        @PropertySource(value = "file:///h:/Proyectos/sabanasblancas/gestionsb-back/src/main/resources/development.properties", ignoreResourceNotFound = true),
        @PropertySource(value = "file:/etc/nidara/sabanasblancas/production.properties", ignoreResourceNotFound = true),
})
public class GestionApplication {

    private static final Logger log = LoggerFactory.getLogger(GestionApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(GestionApplication.class, args);
    }

}
