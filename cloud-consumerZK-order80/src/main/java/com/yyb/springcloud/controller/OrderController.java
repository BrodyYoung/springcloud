package com.yyb.springcloud.controller;

import com.yyb.springcloud.entity.CommonResult;
import com.yyb.springcloud.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {

    @Resource
    private RestTemplate restTemplate;

    //    public static final String PAYMENT_URL = "http://localhost:8001";
//    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";
    public static final String PAYMENT_URL = "http://cloud-payment-service";

    @GetMapping("/consumer/payment/createPayment")
    public CommonResult<Payment> createPayment(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/addPayment", payment, CommonResult.class);
    }

    @GetMapping("/consumer/payment/getPaymentById/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/getPaymentById/" + id, CommonResult.class);
    }

    @GetMapping("/consumer/payment/getZk")
    public String getZkInfo() {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/getZk", String.class);
    }
}
