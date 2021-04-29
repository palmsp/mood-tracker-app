package org.palms.mood.tracker.service.open.weather;

import lombok.extern.slf4j.Slf4j;
import org.palms.mood.tracker.dto.WeatherInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author Maria Pal {@literal <pal.maria.msp@gmail.com>}
 */
@Slf4j
public class OpenWeatherClientImpl implements OpenWeatherClient {

    @Autowired
    private RestTemplate restTemplate;

    private String url;
    private String apiKey;

    public OpenWeatherClientImpl(OpenWeatherClientConfiguration configuration) {
        log.info("Start initializing OpenWeather client");
        this.url = configuration.getUrl();
        this.apiKey = configuration.getApiKey();
        log.info("OpenWeather client successfully initialized");
    }

    @Override
    public WeatherInfo getCurrentWeather(String city) {
        final UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url + "weather")
                .queryParam("units", "metric")
                .queryParam("q", city)
                .queryParam("appid", apiKey);
        log.info("OpenWeather client request: {}", uriBuilder.toUriString());
        ResponseEntity<WeatherInfo> response = restTemplate.getForEntity(uriBuilder.toUriString(), WeatherInfo.class);
        return response.getBody();
    }
}
