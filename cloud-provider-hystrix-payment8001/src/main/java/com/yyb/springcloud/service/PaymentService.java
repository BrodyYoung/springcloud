package com.yyb.springcloud.service;


public interface PaymentService {

    String paymentOk(int id);

    String paymentTimeout(int id);

    String paymentBreaker(int id);

}
