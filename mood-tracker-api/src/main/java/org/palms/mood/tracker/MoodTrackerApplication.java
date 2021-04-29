package org.palms.mood.tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Spring Boot main class for Manager Service.
 *
 * @author Maria Pal {@literal <pal.maria.msp@gmail.com>}
 */
@SpringBootApplication
public class MoodTrackerApplication extends SpringBootServletInitializer {

    /**
     * @param args args for starting
     */
    @SuppressWarnings("uncommentedmain")
    public static void main(String[] args) {
        SpringApplication.run(MoodTrackerApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MoodTrackerApplication.class);
    }
}
