package spring.cloud.gateway.route.config.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * logout cost time of a certain route
 *
 * @author sh
 */
public class XAdBenchmarkTrackingFilter implements GatewayFilter, Ordered {

    private static final String COUNT_START_TIME = "countStartTime";

    public static final Logger LOGGER = LoggerFactory.getLogger(XAdBenchmarkTrackingFilter.class);


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        exchange.getAttributes().put(COUNT_START_TIME, System.currentTimeMillis());
        return chain.filter(exchange).then(
                Mono.fromRunnable(() -> {
                    Long startTime = exchange.getAttribute(COUNT_START_TIME);
                    Long costTime = (System.currentTimeMillis() - startTime);
                    if (startTime != null) {
                        if (costTime >= 200) {
                            LOGGER.warn("no--->>>" + exchange.getRequest().getURI().getRawPath() + ": " + costTime + "ms");
                        } else {
                            LOGGER.info("ok--->>>" + exchange.getRequest().getURI().getRawPath() + ": " + costTime + "ms");
                        }
                    }
                })
        );
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}