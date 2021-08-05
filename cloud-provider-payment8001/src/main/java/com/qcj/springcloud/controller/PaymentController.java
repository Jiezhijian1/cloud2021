package com.qcj.springcloud.controller;

import com.qcj.springcloud.entities.CommonResult;
import com.qcj.springcloud.entities.Payment;
import com.qcj.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("結果:"+result);

        if(result > 0){
            return new CommonResult(200,"success",result);
        }else{
            return new CommonResult(400,"fail",null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("結果:"+payment);

        if(payment != null){
            return new CommonResult(200,"success",payment);
        }else{
            return new CommonResult(400,"noData Id:"+id,null);
        }
    }
}
