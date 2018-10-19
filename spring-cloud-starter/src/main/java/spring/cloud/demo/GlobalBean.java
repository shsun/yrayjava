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
    public Decoder customDecoder(@Autowired StringHttpMessageConverter messageConverter, @Autowired FastJsonHttpMessageConverter4 messageConverter4) {
        HttpMessageConverters decodeConverters = new HttpMessageConverters(false,
                Arrays.asList(messageConverter, messageConverter4));
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
    public RestTemplate restTemplate(@Autowired(required = false) HttpComponentsClientHttpRequestFactory requestFactory,
                                     @Autowired(required = false) FastJsonHttpMessageConverter4 converter4,
                                     @Autowired(required = false) TraceIdHttpRequestInterceptor interceptor) {
        RestTemplate restTemplate;
        if (null == requestFactory) {
            restTemplate = new RestTemplate();
        } else {
            restTemplate = new RestTemplate(requestFactory);
        }
        if (null != converter4) {
            restTemplate.getMessageConverters().add(converter4);
        }
        if (null != interceptor) {
            restTemplate.getInterceptors().add(interceptor);
        }

        return restTemplate;
    }

    @Override
    public void run(String... arg0) {
        // TODO just comment the registerConcurrencyStrategy to work round the 'Another strategy was already registered' bug.
        // HystrixPlugins.getInstance().registerConcurrencyStrategy(new CustomHystrixConcurrencyStrategy());

        System.out.println(arg0);
        System.out.println("这个是测试ApplicationRunner接口---starter");
    }
}
