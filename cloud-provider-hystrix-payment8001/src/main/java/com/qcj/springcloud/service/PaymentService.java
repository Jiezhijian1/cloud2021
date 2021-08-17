package com.qcj.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    public String PaymentInfo_OK(Integer id){
        return "Thread:"+Thread.currentThread().getName()+"PaymentInfo_OK,id:"+id;
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",
            commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")})
    public String paymentInfo_TimeOut(Integer id){
        try{
            TimeUnit.SECONDS.sleep(3);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        return "Thread:"+Thread.currentThread().getName()+"PaymentInfo_OK,id:"+id;
    }

    public String paymentInfo_TimeOutHandler(Integer id){
        return "Thread:"+Thread.currentThread().getName()+"loading,please try again-8001";
    }

    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",
            commandProperties = {
                @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
                @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
                @HystrixProperty(name = "circuitBreaker.sleepWindowInMillisecounds",value = "10000"),
                @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),
            })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if(id < 0){
            throw new RuntimeException("id cannot -");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName()+"\t"+"success,number:"+serialNumber;
    }

    public String paymentCircuitBreak_fallback(@PathVariable("id") Integer id){
        return "id cannot -,please try again! id:"+ id;
    }
}
