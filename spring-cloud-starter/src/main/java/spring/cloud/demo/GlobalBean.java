package spring.cloud.demo;

import java.nio.charset.Charset;
import java.util.Arrays;

import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import spring.cloud.demo.springCloud.CustomHystrixConcurrencyStrategy;
import spring.cloud.demo.springCloud.TraceIdFeignHttpRequrestInterceptor;
import spring.cloud.demo.springCloud.TraceIdHttpRequestInterceptor;
import feign.codec.Decoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;
import com.netflix.hystrix.strategy.HystrixPlugins;

import feign.Request;
import feign.RequestInterceptor;

/**
 * Created by Harry on 2017/6/27.
 */
@Configuration
public class GlobalBean implements CommandLineRunner {


    @Bean
    public StringHttpMessageConverter stringHttpMessageConverter() {
        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        return stringConverter;
    }

    @Bean
    public Decoder customDecoder(@Autowired StringHttpMessageConverter stringHttpMessageConverter, @Autowired FastJsonHttpMessageConverter4 fastConverter) {
        HttpMessageConverters decodeConverters = new HttpMessageConverters(false,
                Arrays.asList(stringHttpMessageConverter, fastConverter));
        ObjectFactory<HttpMessageConverters> objectFactory = () -> decodeConverters;
        return new ResponseEntityDecoder(new SpringDecoder(objectFactory));
    }

    @Bean
    public FastJsonHttpMessageConverter4 fastConverter() {
        FastJsonHttpMessageConverter4 fastConverter = new FastJsonHttpMessageConverter4();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat, SerializerFeature.DisableCircularReferenceDetect);
        //SerializerFeature.PrettyFormat,SerializerFeature.DisableCircularReferenceDetect
//        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteMapNullValue,
//        		SerializerFeature.WriteNullBooleanAsFalse,
//        		SerializerFeature.WriteNullListAsEmpty,
//        		SerializerFeature.WriteNullNumberAsZero,
//        		SerializerFeature.WriteNullStringAsEmpty,
//        		SerializerFeature.DisableCircularReferenceDetect);
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        fastConverter.setFastJsonConfig(fastJsonConfig);
        //默认就是UTF-8
        fastConverter.setDefaultCharset(Charset.forName("UTF-8"));

        return fastConverter;
    }

    @Bean
    public ByteArrayHttpMessageConverter byteArrayHttpMessageConverter() {
        ByteArrayHttpMessageConverter byteArrayHttpMessageConverter = new ByteArrayHttpMessageConverter();
        return byteArrayHttpMessageConverter;
    }

    //For feignClient
    @Bean
    public RequestInterceptor requestInterceptor() {
        return new TraceIdFeignHttpRequrestInterceptor();
    }

    @Bean
    public TraceIdHttpRequestInterceptor traceIdHttpRequestInterceptor() {
        return new TraceIdHttpRequestInterceptor();
    }

    @Bean
    @ConditionalOnProperty(value = {"spring.fegin.request.connectTimeoutMillis", "spring.fegin.request.readTimeoutMillis"})
    @ConfigurationProperties(prefix = "spring.fegin.request")
    public Request.Options feignRequestOptions() {
        return new Request.Options();
    }

    @Bean
    @ConditionalOnProperty(
            value = {"spring.rest.connection.connection-request-timeout",
                    "spring.rest.connection.connect-timeout",
                    "spring.rest.connection.read-timeout"}
    )
    @ConfigurationProperties(prefix = "spring.rest.connection")
    public HttpComponentsClientHttpRequestFactory customHttpRequestFactory() {
        return new HttpComponentsClientHttpRequestFactory();
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(@Autowired(required = false) HttpComponentsClientHttpRequestFactory customHttpRequestFactory,
                                     @Autowired(required = false) FastJsonHttpMessageConverter4 fastConverter,
                                     @Autowired(required = false) TraceIdHttpRequestInterceptor traceIdHttpRequestInterceptor) {

        RestTemplate restTemplate;
        if (null == customHttpRequestFactory) {
            restTemplate = new RestTemplate();
        } else {
            restTemplate = new RestTemplate(customHttpRequestFactory);
        }
        if (null != fastConverter) {
            restTemplate.getMessageConverters().add(fastConverter);
        }
        if (null != traceIdHttpRequestInterceptor) {
            restTemplate.getInterceptors().add(traceIdHttpRequestInterceptor);
        }

        return restTemplate;
    }

    @Override
    public void run(String... arg0) {
        // TODO just comment the registerConcurrencyStrategy to work round the 'Another strategy was already registered' bug.
        /*
         *  java.lang.IllegalStateException: Failed to execute CommandLineRunner
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:821) [spring-boot-2.0.5.RELEASE.jar:2.0.5.RELEASE]
	at org.springframework.boot.SpringApplication.callRunners(SpringApplication.java:802) [spring-boot-2.0.5.RELEASE.jar:2.0.5.RELEASE]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:341) [spring-boot-2.0.5.RELEASE.jar:2.0.5.RELEASE]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1277) [spring-boot-2.0.5.RELEASE.jar:2.0.5.RELEASE]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1265) [spring-boot-2.0.5.RELEASE.jar:2.0.5.RELEASE]
	at spring.cloud.eureka.server.EurekaServerApplication.main(EurekaServerApplication.java:67) [classes/:na]
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[na:1.8.0_65]
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) ~[na:1.8.0_65]
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:1.8.0_65]
	at java.lang.reflect.Method.invoke(Method.java:497) ~[na:1.8.0_65]
	at org.springframework.boot.maven.AbstractRunMojo$LaunchRunner.run(AbstractRunMojo.java:497) [spring-boot-maven-plugin-2.0.5.RELEASE.jar:2.0.5.RELEASE]
	at java.lang.Thread.run(Thread.java:745) [na:1.8.0_65]
Caused by: java.lang.IllegalStateException: Another strategy was already registered.
	at com.netflix.hystrix.strategy.HystrixPlugins.registerConcurrencyStrategy(HystrixPlugins.java:190) ~[hystrix-core-1.5.12.jar:1.5.12]
	at spring.cloud.demo.GlobalBean.run(GlobalBean.java:134) ~[spring.cloud.starter-1.0.0.jar:1.0.0]
         */

        // HystrixPlugins.getInstance().registerConcurrencyStrategy(new CustomHystrixConcurrencyStrategy());
        System.out.println(arg0);
        System.out.println("这个是测试ApplicationRunner接口---starter");
    }
}
