package com.palms.mood.tracker.core.rest.clients.open.weather.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author Maria Pal {@literal <pal.maria.msp@gmail.com>}
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {

    @JsonProperty("temp")
    private Double temperature;

    private Integer pressure;
}
