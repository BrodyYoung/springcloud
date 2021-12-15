package com.yyb.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.yyb.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public String paymentOk(int id) {
        return "线程池：" + Thread.currentThread().getName() + "--OK--" + id;
    }

    @Override
    @HystrixCommand(fallbackMethod = "paymentTimeoutHandle", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "4000")
    })
    public String paymentTimeout(int id) {
//        int i = 10 / 0;
        int timeOut = 5;
        try {
            TimeUnit.SECONDS.sleep(timeOut);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + "--Timeout--" + id;
    }

    // 服务降级
    public String paymentTimeoutHandle(int id) {
        return "线程池：" + Thread.currentThread().getName() + "--服务出错--" + id;
    }

    // 服务熔断
    @Override
    @HystrixCommand(fallbackMethod = "paymentBreakerFallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
    })
    public String paymentBreaker(int id) {
        if (id < 0) {
            throw new RuntimeException("id不能为负数"); // 出现异常，调用服务降级方法
//            return "主方法：id不能为负数";
        }
        String s = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" + s;
    }

    public String paymentBreakerFallback(int id) {
        return "id不能为负数，请稍后再试";
    }
}
