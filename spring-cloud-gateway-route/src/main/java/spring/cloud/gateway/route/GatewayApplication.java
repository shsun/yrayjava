package spring.cloud.gateway.route;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import spring.cloud.gateway.route.filter.XAdBenchmarkTrackingFilter;

@EnableEurekaClient
@SpringBootApplication
public class GatewayApplication {


    @Bean
    public RouteLocator customeRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/biz/**")
                        .filters(f -> f.stripPrefix(1)
                                .filter(new XAdBenchmarkTrackingFilter())
                                .addResponseHeader("X-Response-test", "test-biz"))
                        .uri("lb://spring.cloud.biz")
                        .order(0)
                        .id("spring.cloud.biz")
                ).route(r -> r.path("/dsp/**")
                        .filters(f -> f.stripPrefix(1)
                                .filter(new XAdBenchmarkTrackingFilter())
                                .addResponseHeader("X-Response-test", "test-dsp"))
                        .uri("lb://spring.cloud.dsp")
                        .order(0)
                        .id("spring.cloud.dsp")
                )
                .build();
    }

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
