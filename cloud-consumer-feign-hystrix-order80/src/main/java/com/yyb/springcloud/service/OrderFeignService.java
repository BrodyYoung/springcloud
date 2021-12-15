package com.yyb.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("cloud-payment-hystrix-service")
public interface OrderFeignService {

    @GetMapping("/payment/paymentOk/{id}")
    String paymentOk(@PathVariable("id") int id);

    @GetMapping("/payment/paymentTimeout/{id}")
    String paymentTimeout(@PathVariable("id") int id);

}
