package org.palms.mood.tracker.service.open.weather;

import org.palms.mood.tracker.dto.WeatherInfo;

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
