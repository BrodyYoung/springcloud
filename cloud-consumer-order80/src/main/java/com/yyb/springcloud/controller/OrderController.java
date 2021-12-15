package com.yyb.springcloud.controller;

import com.yyb.springcloud.entity.CommonResult;
import com.yyb.springcloud.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
@EnableEurekaClient
public class OrderController {

    @Resource
    private RestTemplate restTemplate;

    //    public static final String PAYMENT_URL = "http://localhost:8001";
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @GetMapping("/consumer/payment/createPayment")
    public CommonResult<Payment> createPayment(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/addPayment", payment, CommonResult.class);
    }

    @GetMapping("/consumer/payment/getPaymentById/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/getPaymentById/" + id, CommonResult.class);
    }


    @GetMapping("/consumer/payment/createPayment2")
    public ResponseEntity<Payment> createPayment2(Payment payment) {
        return restTemplate.postForEntity(PAYMENT_URL + "/payment/addPayment", payment, Payment.class);
    }

    @GetMapping("/consumer/payment/getPaymentById2/{id}")
    public CommonResult<Payment> getPaymentById2(@PathVariable("id") Long id) {
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/getPaymentById/" + id, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            return entity.getBody();
        } else {
            return new CommonResult<Payment>(422, "获取失败");
        }
    }
}
