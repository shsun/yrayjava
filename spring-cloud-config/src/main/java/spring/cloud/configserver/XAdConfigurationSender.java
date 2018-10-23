package spring.cloud.configserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author sh
 */
@Component
public class XAdConfigurationSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(XAdConfigurationSender.class);

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send() {
        String context = "hello " + new Date();
        LOGGER.info("XAdConfigurationSender : " + context);
        this.amqpTemplate.convertAndSend("hello", context);
    }
}

