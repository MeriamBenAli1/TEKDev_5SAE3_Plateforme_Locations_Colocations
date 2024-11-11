package tn.esprit.ms.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@SpringBootApplication
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("GestionUser", r -> r.path("/Users/**").uri("http://localhost:9912"))
                .route("GestionUser", r -> r.path("/Users/**").uri("http://localhost:8078"))
                .route("MicroserviceMesssagerie", r -> r.path("/api/messages/**").uri("http://localhost:8085"))
                .route("Reservation", r -> r.path("/reservations/**").uri("http://localhost:8556"))
                .route("Annonce", r -> r.path("/annonces/**").uri("http://localhost:8081"))
                .route("MSBlog", r -> r.path("/blog/**").uri("http://localhost:8087"))
                .route("LogsNode", r -> r.path("/logs/**").uri("http://localhost:3000"))
                .build();
    }
}
