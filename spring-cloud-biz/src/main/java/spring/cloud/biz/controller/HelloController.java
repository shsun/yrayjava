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


    @RequestMapping("/kk")
    public String getAddressKK() {
        return "kk--" + this.address;
    }


    @RequestMapping("/biz/k/1")
    public String getAddressKK_1() {
        return "/biz/k/1--" + this.address;
    }


    @RequestMapping("/biz/k/2")
    public String getAddressKK_2() {
        return "biz/k/2--" + this.address;
    }

}
