package com.palms.mood.tracker.core.rest.clients.open.weather.client;

import com.palms.mood.tracker.core.rest.clients.open.weather.client.model.WeatherInfo;

/**
 * @author Maria Pal {@literal <pal.maria.msp@gmail.com>}
 */
public interface OpenWeatherClient {

    /**
     * Current weather.
     *
     * @param city name of city
     * @return {@link WeatherInfo}
     */
    WeatherInfo getCurrentWeather(String city);
}
