package com.yyb.springcloud.service;

import com.yyb.springcloud.entity.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {

    public int addPayment(Payment payment);

    public Payment getPaymentById(Long id);

}
