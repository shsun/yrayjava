package spring.cloud.configserver.configure;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author sh
 */
@Configuration
public class Configure {
    @Bean
    public Queue helloQueue() {
        return new Queue("hello");
    }
}
