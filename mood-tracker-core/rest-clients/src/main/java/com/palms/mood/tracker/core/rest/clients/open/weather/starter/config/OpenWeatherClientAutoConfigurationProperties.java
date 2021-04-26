package com.palms.mood.tracker.core.rest.clients.open.weather.starter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * @author Maria Pal {@literal <pal.maria.msp@gmail.com>}
 */
@Data
@Validated
@ConfigurationProperties(prefix = "open-weather-client")
public class OpenWeatherClientAutoConfigurationProperties {

    @NotNull
    private String url;

    @NotNull
    private String apiKey;

    @NotNull
    private int connectionTimeOut;

    @NotNull
    private int readTimeOut;
}