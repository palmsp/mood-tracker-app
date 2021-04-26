package com.palms.mood.tracker.core.rest.clients.open.weather.starter;

import com.palms.mood.tracker.core.rest.clients.open.weather.client.OpenWeatherClient;
import com.palms.mood.tracker.core.rest.clients.open.weather.client.OpenWeatherClientImpl;
import com.palms.mood.tracker.core.rest.clients.open.weather.client.config.OpenWeatherClientConfiguration;
import com.palms.mood.tracker.core.rest.clients.open.weather.starter.config.OpenWeatherClientAutoConfigurationProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author Maria Pal {@literal <pal.maria.msp@gmail.com>}
 */
@Configuration
@EnableConfigurationProperties(OpenWeatherClientAutoConfigurationProperties.class)
public class OpenWeatherClientAutoConfiguration {

    /**
     * @param properties {@link OpenWeatherClientConfiguration}
     * @return {@link OpenWeatherClientConfiguration}
     */
    @Bean
    @Primary
    @ConditionalOnMissingBean(OpenWeatherClientConfiguration.class)
    public OpenWeatherClientConfiguration openWeatherClientConfiguration(OpenWeatherClientAutoConfigurationProperties properties) {
        return new OpenWeatherClientConfiguration(properties.getUrl(), properties.getApiKey(), properties.getConnectionTimeOut(),
                properties.getReadTimeOut());
    }

    /**
     * @return {@link RestTemplate}
     */
    @Bean
    @Primary
    @ConditionalOnMissingBean(RestTemplate.class)
    RestTemplate restTemplate(OpenWeatherClientConfiguration configuration) {
        final SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(configuration.getReadTimeOut());
        factory.setConnectTimeout(configuration.getConnectionTimeOut());
        return new RestTemplate(factory);
    }

    /**
     * @param configuration {@link OpenWeatherClientConfiguration}
     * @return {@link OpenWeatherClient}
     */
    @Bean
    @ConditionalOnMissingBean(OpenWeatherClientImpl.class)
    public OpenWeatherClient openWeatherClient(OpenWeatherClientConfiguration configuration) {
        return new OpenWeatherClientImpl(configuration);
    }
}
