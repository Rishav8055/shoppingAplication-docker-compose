package com.niit.SpringCloudAPIGateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder routeLocatorBuilder){
        return routeLocatorBuilder.routes()
                .route(p->p
                .path("/userservice/user/**")
                .uri("http://authentication-service:9200/"))

                .route(p->p
                        .path("/userproduct/user/**")
                        .uri("http://product-service:9100/"))
                .build();
    }
}
