package org.palms.mood.tracker.config;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Maria Pal {@literal <pal.maria.msp@gmail.com>}
 */
@Configuration
@Slf4j
@EnableSwagger2
public class SwaggerConfiguration {

    private ApiInfoBuilder apiInfo() {
        return new ApiInfoBuilder()
                .description("MOOD TRACKER API")
                .title("MOOD TRACKER API");
    }

    /**
     * Create swagger docket.
     * @return {@link Docket} Docket
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(Docket.DEFAULT_GROUP_NAME)
                .apiInfo(apiInfo().build())
                .useDefaultResponseMessages(false)
                .select()
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .build();
    }
}
