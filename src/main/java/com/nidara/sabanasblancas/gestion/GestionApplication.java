package com.nidara.sabanasblancas.gestion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.TimeZone;

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

    @PostConstruct
    public void init(){
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Madrid"));
        log.info("Spring boot application running in Madrid timezone :" + new Date());
    }

}
