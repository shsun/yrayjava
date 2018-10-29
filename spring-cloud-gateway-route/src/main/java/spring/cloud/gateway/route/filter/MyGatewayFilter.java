package spring.cloud.gateway.route.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.stereotype.Component;

@Component
public class MyGatewayFilter implements GatewayFilterFactory {

    @Override
    public GatewayFilter apply(Object config) {
        return null;
    }
}
