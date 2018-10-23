package spring.cloud.biz.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class HelloController {
    @Value("${__name__}")
    private String address;

    @RequestMapping("/k")
    public String getAddress() {
        return this.address;
    }
}
