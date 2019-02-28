package com.nidara.sabanasblancas.gestion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.nidara.sabanasblancas.gestion")
public class GestionApplication {

    private static final Logger log = LoggerFactory.getLogger(GestionApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(GestionApplication.class, args);
    }

}
