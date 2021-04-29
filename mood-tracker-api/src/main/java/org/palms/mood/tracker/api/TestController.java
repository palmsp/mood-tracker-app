package org.palms.mood.tracker.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.palms.mood.tracker.dto.WeatherInfo;
import org.palms.mood.tracker.service.open.weather.OpenWeatherService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for test.
 *
 * @author Maria Pal {@literal <pal.maria.msp@gmail.com>}
 */
@RestController
@RequestMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Api("Test controller")
@Slf4j
public class TestController {

    private final OpenWeatherService openWeatherService;

    /**
     * Greetings.
     *
     * @param name name
     * @param location location
     * @return greetings
     */
    @ApiOperation("Greetings")
    @GetMapping(value = "/firstGreetings")
    public String test(@RequestParam(value = "name") String name,
                       @RequestParam(value = "location") String location) {
        log.info("TestController.test() with param {}, {}", name, location);
        WeatherInfo weatherInfo = openWeatherService.getCurrentWeather(location);
        final StringBuilder response = new StringBuilder()
                .append("hello ")
                .append(name)
                .append("! ")
                .append("Current weather in ")
                .append(weatherInfo.getName())
                .append(": ")
                .append(weatherInfo.getWeather().getTemperature())
                .append("°C");
        return response.toString();
    }
}
