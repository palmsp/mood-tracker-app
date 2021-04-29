package org.palms.mood.tracker.service.open.weather;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.palms.mood.tracker.dto.WeatherInfo;
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
