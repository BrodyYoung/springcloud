package com.yyb.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.yyb.springcloud.service.OrderFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/consumer")
@Slf4j
@EnableEurekaClient
@DefaultProperties(defaultFallback = "defaultHandle")
public class OrderController {
    @Resource
    private OrderFeignService orderFeignService;

    @GetMapping("/paymentOk/{id}")
    public String paymentOk(@PathVariable("id") int id) {
        return orderFeignService.paymentOk(id);
    }

    @GetMapping("/paymentTimeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentTimeoutHandle", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
//    })
    @HystrixCommand
    public String paymentTimeout(@PathVariable("id") int id) {
        return orderFeignService.paymentTimeout(id);
    }

    public String paymentTimeoutHandle(@PathVariable("id") int id) {
        return "消费者80服务出错,请稍后再试";
    }

    public String defaultHandle() {
        return "全局降级方法";

    }
}
