package com.palms.mood.tracker.core.rest.clients.open.weather.client.config;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Maria Pal {@literal <pal.maria.msp@gmail.com>}
 */
@Data
@AllArgsConstructor
public class OpenWeatherClientConfiguration {

    private String url;
    private String apiKey;
    private int connectionTimeOut;
    private int readTimeOut;
}
