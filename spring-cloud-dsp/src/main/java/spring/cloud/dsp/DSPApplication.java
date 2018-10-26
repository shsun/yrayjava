package spring.cloud.dsp;

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


//@EnableEurekaClient
//@ServletComponentScan
@SpringBootApplication
@EnableScheduling
@MapperScan("spring.cloud.dsp.dataaccess.mapper")
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
@EnableHystrixDashboard
public class DSPApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DSPApplication.class, args);
    }

    @Override
    public void run(String... arg0) {
        //do something after spring-boot started
    }

}