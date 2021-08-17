package com.qcj.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService{

    @Override
    public String paymentInfo_OK(Integer id){
        return "fall back paymentInfo_OK-80";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id){
        return "fall back paymentInfo_TimeOut-80";
    }
}
