package spring.cloud.eureka.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Harry on 08/09/2017.
 */
@RestController
public class HealthController {

    @GetMapping("/health")
    public String health(HttpServletRequest request, HttpServletResponse response) {
        return "success";
    }

}
