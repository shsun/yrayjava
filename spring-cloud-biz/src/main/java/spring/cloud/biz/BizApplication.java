package spring.cloud.biz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;


@EnableEurekaClient
@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
@MapperScan("spring.cloud.biz.dataaccess.mapper")
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
@EnableHystrixDashboard
public class BizApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(BizApplication.class, args);
    }

    //这里就是创建一个负载均衡的RestTemplate Bean
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Override
    public void run(String... arg0) {
        //do something after spring-boot started
    }

}