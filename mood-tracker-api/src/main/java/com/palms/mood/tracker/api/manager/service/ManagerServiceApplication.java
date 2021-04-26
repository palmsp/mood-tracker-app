package com.palms.mood.tracker.api.manager.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.WebApplicationInitializer;

/**
 * Spring Boot main class for Manager Service.
 *
 * @author Maria Pal {@literal <pal.maria.msp@gmail.com>}
 */
@SpringBootApplication
public class ManagerServiceApplication extends SpringBootServletInitializer implements WebApplicationInitializer {

    /**
     * @param args args for starting
     */
    @SuppressWarnings("uncommentedmain")
    public static void main(String[] args) {
        SpringApplication.run(ManagerServiceApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ManagerServiceApplication.class);
    }
}
