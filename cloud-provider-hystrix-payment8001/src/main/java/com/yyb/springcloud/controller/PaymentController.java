package com.yyb.springcloud.controller;

import com.yyb.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;    //注意要引入spring的包

    @Value("${server.port}")
    private String serverPort;


    @GetMapping("/paymentOk/{id}")
    public String paymentOk(@PathVariable("id") int id) {
        return paymentService.paymentOk(id);
    }

    // 服务降级
    @GetMapping("/paymentTimeout/{id}")
    public String paymentTimeout(@PathVariable("id") int id) {
        return paymentService.paymentTimeout(id);
    }

    // 服务熔断
    @GetMapping("/paymentBreaker/{id}")
    public String paymentBreaker(@PathVariable("id") int id) {
        return paymentService.paymentBreaker(id);
    }
}
