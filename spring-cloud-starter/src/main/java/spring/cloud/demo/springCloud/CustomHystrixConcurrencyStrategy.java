package spring.cloud.demo.springCloud;

import java.util.concurrent.Callable;

import spring.cloud.demo.model.TraceIdHelper;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;


public class CustomHystrixConcurrencyStrategy extends HystrixConcurrencyStrategy {

    @Override
    public <T> Callable<T> wrapCallable(Callable<T> callable) {
        CustomCallable<T> customCallable = new CustomCallable<T>(callable,
                TraceIdHelper.getTraceId(),
                TraceIdHelper.getRemoteIp());
        return super.wrapCallable(customCallable);
    }

}