package com.zzqfsy.cloud.eureka.client.rpc.hytrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zzqfsy.cloud.eureka.client.rpc.feign.HelloFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("hystrixHelloClient")
public class HelloHystrixWrappedClient {

    @Autowired
    private HelloFeignClient helloFeignClient;

    @HystrixCommand(groupKey = "helloGroup", fallbackMethod = "fallBackCall")
    public String hello() {
        return this.helloFeignClient.hello();
    }

    public String fallBackCall() {
        return "FAILED SERVICE CALL! - FALLING BACK";
    }

    /*@HystrixCommand(groupKey = "helloGroup", fallbackMethod = "fallBackCall",
        commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500")
        },
        threadPoolProperties = {
            @HystrixProperty(name = "coreSize", value = "30"),
            @HystrixProperty(name = "maxQueueSize", value = "101"),
            @HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),
            @HystrixProperty(name = "queueSizeRejectionThreshold", value = "15"),
            @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1440")
        })
    public String helloRibbon() {
        return this.helloFeignClient.hello();
    }*/
}
