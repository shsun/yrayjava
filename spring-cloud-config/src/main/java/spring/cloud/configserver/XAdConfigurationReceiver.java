package spring.cloud.configserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author sh
 */
@Component
@RabbitListener(queues = "hello")
public class XAdConfigurationReceiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(XAdConfigurationReceiver.class);

    @RabbitHandler
    public void process(String hello) {
        LOGGER.info("XAdConfigurationReceiver : " + hello);
    }
}

