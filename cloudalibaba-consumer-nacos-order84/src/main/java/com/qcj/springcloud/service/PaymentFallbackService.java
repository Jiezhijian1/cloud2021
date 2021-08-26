package com.qcj.springcloud.service;

import com.qcj.springcloud.entities.CommonResult;
import com.qcj.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentService{
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(444,"PaymentFallbackService",new Payment(id,"errorSerial"));
    }
}
