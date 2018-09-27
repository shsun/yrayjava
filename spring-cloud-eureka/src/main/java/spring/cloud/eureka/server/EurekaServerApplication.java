package spring.cloud.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import java.io.InputStream;
import java.util.Properties;

@SpringBootApplication
@EnableEurekaServer
@EnableConfigServer
public class EurekaServerApplication {

    private static final String MODE_DEBUG = "debug";
    private static final String MODE_RELEASE = "release";


    public static void main(String[] args) {
        try {
            Properties properties = new Properties();
            InputStream in = EurekaServerApplication.class.getClassLoader().getResourceAsStream("application.properties");
            properties.load(in);

            String mode = properties.getProperty("custom.mode");
            System.out.println(mode);

            int peer = Integer.parseInt(properties.getProperty("custom.peer"));
            System.out.println(peer);

            System.exit(0);

            if (MODE_DEBUG.equalsIgnoreCase(mode)) {
                in = EurekaServerApplication.class.getClassLoader().getResourceAsStream("profiles/dev/application.properties");
                properties.load(in);
            } else if (MODE_RELEASE.equalsIgnoreCase(mode)) {
                switch (peer) {
                    case 1:
                        in = EurekaServerApplication.class.getClassLoader().getResourceAsStream("application-release-pee1.properties");
                        break;
                    case 2:
                        in = EurekaServerApplication.class.getClassLoader().getResourceAsStream("application-release-pee2.properties");
                        break;
                    default:
                        in = EurekaServerApplication.class.getClassLoader().getResourceAsStream("profiles/dev/application.properties");
                        break;
                }
                properties.load(in);
            } else {
                System.exit(0);
            }


            System.out.println("-->>>>>>>>>>>>>>>>" + properties.getProperty("a"));


            SpringApplication application = new SpringApplication(EurekaServerApplication.class);
            application.setDefaultProperties(properties);
            application.run(args);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            System.exit(0);
        }


//        SpringApplication.run(EurekaServerApplication.class, args);
//

    }

}
