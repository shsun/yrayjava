package spring.cloud.apk.controller;

import org.junit.Assert;
import org.junit.Test;

import spring.cloud.apk.BaseIntegrationTest;

public class InternationalDemoTest extends BaseIntegrationTest {

    @Test
    public void zh() {
        String tmpURL = url + "/in/lang?lang=zh_CN";
        System.out.println("tmpURL-->" + tmpURL);
        Object actual = restTemplate.getForEntity(tmpURL, String.class).getBody();
        System.out.println("actual-->" + actual);
        Assert.assertEquals("测试", actual);
    }

    @Test
    public void en() {
        String tmpURL = url + "/in/lang?lang=en_US";
        Object actual = restTemplate.getForEntity(tmpURL, String.class).getBody();
        Assert.assertEquals("test", actual);
    }
}
