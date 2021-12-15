package com.yyb.springcloud.controller;

import com.yyb.springcloud.entity.CommonResult;
import com.yyb.springcloud.entity.Payment;
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
public class OrderController {
    @Resource
    private OrderFeignService orderFeignService;

    @GetMapping("/payment/getPaymentById/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return orderFeignService.getPaymentById(id);
    }

    @GetMapping("/payment/timeout")
    String timeout() {
        return orderFeignService.timeout();
    }
}
