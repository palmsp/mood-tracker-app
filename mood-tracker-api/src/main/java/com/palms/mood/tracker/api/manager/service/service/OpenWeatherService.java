package com.palms.mood.tracker.api.manager.service.service;

import com.palms.mood.tracker.core.rest.clients.open.weather.client.OpenWeatherClient;
import com.palms.mood.tracker.core.rest.clients.open.weather.client.model.WeatherInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Maria Pal {@literal <pal.maria.msp@gmail.com>}
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class OpenWeatherService {

    private final OpenWeatherClient openWeatherClient;

    /**
     * @param city city
     * @return {@link WeatherInfo}
     */
    public WeatherInfo getCurrentWeather(String city) {
        return openWeatherClient.getCurrentWeather(city);
    }
}
