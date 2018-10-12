package spring.cloud.engine;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableEurekaClient
@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
@MapperScan("spring.cloud.engine.dataaccess.mapper")
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
@EnableHystrixDashboard
@ServletComponentScan
public class EngineApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(EngineApplication.class, args);
    }

    @Override
    public void run(String... arg0) {
        //do something after spring-boot started
    }

}