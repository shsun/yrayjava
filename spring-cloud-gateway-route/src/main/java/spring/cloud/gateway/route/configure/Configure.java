package spring.cloud.gateway.route.configure;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.cloud.gateway.route.config.filter.XAdBenchmarkTrackingFilter;

/**
 * @author shsun
 */
@Configuration
public class Configure {

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
}



