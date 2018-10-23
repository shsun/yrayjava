package spring.cloud.configserver.configure;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import spring.cloud.configserver.ConfigServerApplication;
import spring.cloud.configserver.XAdConfigurationSender;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = ConfigServerApplication.class)
public class ApplicationTests {

    @Autowired
    private XAdConfigurationSender sender;

    @Test
    public void hello() throws Exception {
        sender.send();
    }

    @Test
    public void contextLoads() {
    }

}

