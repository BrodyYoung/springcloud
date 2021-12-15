package com.yyb.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {
    @Bean
    @LoadBalanced
    /* 开启负载均衡，使得服务消费者采用负载均衡（轮询）的方式调用服务提供者的服务。 */
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
