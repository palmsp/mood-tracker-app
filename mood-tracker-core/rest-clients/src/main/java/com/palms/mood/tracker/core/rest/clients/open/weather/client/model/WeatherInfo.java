package com.palms.mood.tracker.core.rest.clients.open.weather.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author Maria Pal {@literal <pal.maria.msp@gmail.com>}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class WeatherInfo {

    private Long id;
    private String name;

    @JsonProperty("main")
    private Weather weather;
}
