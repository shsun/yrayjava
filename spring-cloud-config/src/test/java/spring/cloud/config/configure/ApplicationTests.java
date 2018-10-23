package spring.cloud.config.configure;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import spring.cloud.config.ConfigServerApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ConfigServerApplication.class)
public class ApplicationTests {

    @Test
    public void contextLoads() {
    }

}

