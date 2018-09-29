package spring.cloud.eureka.server;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.config.server.EnableConfigServer;

import java.io.InputStream;
import java.util.Properties;


/**
 * @author sh
 */
@SpringBootApplication
@EnableEurekaServer
@EnableConfigServer
@EnableEurekaClient
public class EurekaServerApplication {

    private static final String MODE_DEV = "dev";
    private static final String MODE_PRD = "prd";

    /*
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String profiles = scanner.nextLine();
        new SpringApplicationBuilder(EurekaServerApplication.class).profiles(profiles).run(args);
    }
    */


    public static void main(String[] args) {
        try {
            //
            Properties properties = new Properties();
            InputStream in = EurekaServerApplication.class.getClassLoader().getResourceAsStream("application_config.properties");
            properties.load(in);
            //
            String mode = properties.getProperty("custom.mode");
            String peer = properties.getProperty("custom.peer");
            //
            String fileName = "dev/application.properties";
            if (MODE_PRD.equalsIgnoreCase(mode)) {
                fileName = "prd/application_peer" + peer + ".properties";
            }
            in = EurekaServerApplication.class.getClassLoader().getResourceAsStream(fileName);
            properties.load(in);
            System.out.println("\n\n\n------------------->>" + peer + "\n\n\n__file_name__=" + properties.getProperty("__file_name__") + "\n\n\n");
//
//            Scanner scanner = new Scanner(System.in);
//            String profiles = scanner.nextLine();

            //
            SpringApplication application = new SpringApplication(EurekaServerApplication.class);
            application.setDefaultProperties(properties);
            application.run(args);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            System.exit(0);
        }

        // SpringApplication.run(EurekaServerApplication.class, args);
    }
}
